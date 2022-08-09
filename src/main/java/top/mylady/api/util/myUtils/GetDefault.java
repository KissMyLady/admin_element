package top.mylady.api.util.myUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;


/**
 * @Author: KissMyLady
 * @Date: Created in 2022/6/10 15:53
 * @Description:
 */
public class GetDefault {

    private static final Logger logger = LoggerFactory.getLogger(GetDefault.class);

    private static final String UNKNOWN = "unknown";

    //返回默认字符串
    public static String getDefault(String strObj, String defaultObj) {
        if (strObj != null && !strObj.equals("")) {
            return strObj;
        } else {
            return defaultObj;
        }
    }


    /**
     * 获取URLEncoder编码后的字符串对象
     */
    public static String encode(String strObj, String defaultObj) {
        try {
            String encode = URLEncoder.encode(strObj, "UTF-8");
            return encode;
        } catch (Exception e) {
            logger.error("URLEncoder.encode编码错误, " + e.getMessage());
        }
        return defaultObj;
    }

    public static String encode(String strObj) {
        try {
            String encode = URLEncoder.encode(strObj, "UTF-8");
            return encode;
        } catch (Exception e) {
            logger.error("URLEncoder.encode编码错误, " + e.getMessage());
        }
        return "";
    }

    //返回时间戳
    public static String getStampTime() {
        return System.currentTimeMillis() + "";
    }

}
