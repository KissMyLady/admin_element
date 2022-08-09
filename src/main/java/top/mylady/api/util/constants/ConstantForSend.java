package top.mylady.api.util.constants;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/6/16 8:35
 * @Description:
 */
public interface ConstantForSend {

    //WebSock
    String PUSH_SERVER = "/topic/server";

    //log日志
    public String lv_debug = "debug";
    public String lv_info = "info";
    public String lv_warn = "warn";
    public String lv_error = "error";


    //默认标题
    public String title_debug = "";
    public String title_info = "";
    public String title_warn = "";
    public String title_error = "";

    //请求方式
    public String req_post = "post";
    public String req_get = "get";
}
