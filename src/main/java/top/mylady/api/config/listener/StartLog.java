package top.mylady.api.config.listener;

import cn.hutool.core.thread.ThreadUtil;
import org.springframework.stereotype.Component;
import cn.hutool.core.date.BetweenFormatter;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.net.NetUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/7/15 22:29
 * @Description:
 */
@Component
public class StartLog {

    /** 系统名称 */
    private static String systemName;
    /** 服务端口 */
    private static String serverPort;
    /** 服务根地址 */
    private static String serverContextPath;
    /** 系统启动时间 */
    private static String starterDate;


    /**
     * 获得服务根路径
     * @return String
     */
    public String getBasePath(){
        return NetUtil.getLocalhostStr() + ":" + serverPort; //+ serverContextPath;
    }

    /**
     * 获得当前系统运行时间
     * @return String
     */
    public String getRunTime(){
        //Level.MINUTE表示精确到分
        return DateUtil.formatBetween(DateUtil.parseDateTime(starterDate), DateUtil.date(), BetweenFormatter.Level.MINUTE);
    }

    //获得实例对象
    public static StartLog getInstance(){
        return StartPrintInner.INSTANCE;
    }

    //静态内部类
    private static class StartPrintInner{
        /** 实例对象 */
        private static final StartLog INSTANCE = new StartLog();
    }

    @Value("${server.port}")
    public void setServerPort(String serverPort) {
        StartLog.serverPort = serverPort;
    }

    @Value("${server.servlet.context-path}")
    public void setServerContextPath(String serverContextPath) {
        StartLog.serverContextPath = serverContextPath;
    }


    /**
     * 成功
     * 打印启动日志
     */
    public void successPrint(){
        // 睡一秒打印
        ThreadUtil.sleep(1, TimeUnit.SECONDS);
        String basePath = getBasePath();
        String printStr =
                "\n----------------------------------------------------------\n" +
                        systemName + " 启动成功! 相关URLs:\n" +
                        "项目地址: \t\thttp://" + basePath + "/\n" +
                        "Hello World: \thttp://" + basePath + "/api/marry/hello \n" +
                        "----------------------------------------------------------\n";
        Console.log(printStr);
    }

    /**
     * 失败
     * 打印启动日志
     */
    public void errorPrint(){
        // 睡一秒打印
        ThreadUtil.sleep(1, TimeUnit.SECONDS);
        String printStr =
                "\n----------------------------------------------------------\n" +
                        systemName + " 启动失败! 请检查相关配置！\n" +
                        "----------------------------------------------------------\n";
        Console.log(printStr);
    }

    /***
     * 初始化
     * @param globalProperties 配置类
     */
    @Autowired
    public void init(GlobalProperties globalProperties){
        if(globalProperties != null){
            // 设置系统名称
            this.setSystemName(globalProperties.getSystemName());
            // 设置系统启动时间
            this.setStarterDate(globalProperties.getSystemStarterTime());
        }
    }

    private void setSystemName(String systemName) {
        StartLog.systemName = systemName;
    }

    private void setStarterDate(String starterDate) {
        // 非空验证
        if(StringUtils.isEmpty(starterDate)){
            StartLog.starterDate = DateUtil.date().toString();
            return;
        }

        // 非法时间参数验证
        Date tmp = null;
        try {
            tmp = DateUtil.parseDateTime(starterDate);
        }catch (Exception ignored){}
        if(tmp == null){
            StartLog.starterDate = DateUtil.date().toString();
            return;
        }
        StartLog.starterDate = starterDate;
    }

}
