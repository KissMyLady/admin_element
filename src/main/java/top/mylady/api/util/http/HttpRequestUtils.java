package top.mylady.api.util.http;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.Map;
import java.util.Set;


/**
 * @Author: KissMyLady
 * @Date: Created in 2021/11/24 11:00
 * @Description: Post请求 与 Get请求 封装
 */
public class HttpRequestUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpRequestUtils.class);

    //获取 form-data 类型的
    public String postFormData(String url, Map<String, String> map) throws Exception{
        //创建httpclient对象
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();

        if(ObjectUtil.isNotEmpty(map)){
            Set<Map.Entry<String, String>> entries = map.entrySet();

            ContentType contentType = ContentType.create(HTTP.PLAIN_TEXT_TYPE, HTTP.UTF_8);
            for(Map.Entry<String, String> entry: entries){
                builder.addTextBody(""+entry.getKey(), entry.getValue(), contentType);
            }
        }
        //设置浏览器兼容模式
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.setCharset(Consts.UTF_8);
        builder.setContentType(ContentType.MULTIPART_FORM_DATA);

        //添加文件
        //builder.addBinaryBody("file", file);
        HttpEntity multipart = builder.build();
        httpPost.setEntity(multipart);
        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = client.execute(httpPost);
        HttpEntity entity = response.getEntity();

        String body = "";
        if (entity != null) {
            //按指定编码转换结果实体为String类型
            body = EntityUtils.toString(entity);
        }
        EntityUtils.consume(entity);
        response.close();
        return body;
    }



    /**
     * @Description: Get 请求
     * @param url 字符串类型的url
     * @param map k:v params的map数组对象
     * @return 字符串类型的返回值
     */
    public String getRequest(String url, Map<String, String> map) throws Exception{
        //创建http链接对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //方法一:
        //创建URLBuilder
        URIBuilder uriBuilder;
        try {
            uriBuilder = new URIBuilder(url);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

        //遍历map对象
        if(ObjectUtil.isNotEmpty(map)){
            Set<Map.Entry<String, String>> entries = map.entrySet();
            for(Map.Entry<String, String> entry: entries){
                System.out.println("设置params参数: key: "+ entry.getKey()+ " value: "+ entry.getValue());
                uriBuilder.setParameter(entry.getKey(), entry.getValue());
            }
        }
        //创建HttpGet对象，设置URL访问地址
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        //httpGet.setHeader("Content-type", "application/json");
        httpGet.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //发起请求
        try {
            HttpResponse result = httpClient.execute(httpGet);
            HttpEntity ret = result.getEntity();
            String retStr = EntityUtils.toString(ret);
            return retStr;
        }
        catch (Exception e){
            e.printStackTrace();
            throw new Exception("请求错误, 原因是: "+ e);
        }
    }

    /**
     * @Description: Post请求
     * @param url 字符串类型的url
     * @param map k:v params的map数组对象
     * @return 字符串类型的返回值
     */
    public String postRequest(String url, Map<String, String> map) throws Exception{

        //创建httpclient对象
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        JSONObject params = new JSONObject();
        //遍历map对象
        if(ObjectUtil.isNotEmpty(map)){
            Set<Map.Entry<String, String>> entries = map.entrySet();
            for(Map.Entry<String, String> entry: entries){
                //System.out.println("设置params参数: key: "+ entry.getKey()+ " value: "+ entry.getValue());
                params.put(entry.getKey(), entry.getValue());
            }
        }

        //装填参数
        StringEntity s = new StringEntity(params.toString(), "utf-8");
        s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,  "application/json"));

        //设置参数到请求对象中
        httpPost.setEntity(s);
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = client.execute(httpPost);
        //获取结果实体
        HttpEntity entity = response.getEntity();

        String body = "";
        if (entity != null) {
            //按指定编码转换结果实体为String类型
            body = EntityUtils.toString(entity);
        }
        EntityUtils.consume(entity);
        response.close();
        return body;
    }

    /**
     * 获取ip地址
     * 参考博客: https://blog.csdn.net/nongminkouhao/article/details/106738635
     */
    public static String getIpAddress(HttpServletRequest request){
        String ipAddress = null;
        ipAddress = request.getHeader("x-forwarded-for");
        try {
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }

            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        }
        catch (Exception e){
            logger.error("工具类获取ip地址错误, 原因是: "+ e);
            ipAddress = null;
        }
        //logger.info("打印获取到的ip地址: "+ ipAddress);
        return ipAddress;
    }
}
