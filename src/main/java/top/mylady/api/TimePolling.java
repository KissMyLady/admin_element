package top.mylady.api;

import top.mylady.api.service.utilsService.impl.UtilsLogServiceImpl;
import top.mylady.api.util.constants.ConstantForSend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import javax.annotation.Resource;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/6/10 9:55
 * @Description: 定时消息轮询
 */
//@Component
public class TimePolling {

    private static final Logger logger = LoggerFactory.getLogger(TimePolling.class);

    @Autowired
    private SimpMessagingTemplate wsTemplate;

    @Resource
    private UtilsLogServiceImpl logService;

    /**
     * 服务器定时推送任务
     */
    //@Scheduled(cron = "0/3 * * * * ?")
    public void websocket() throws Exception {
        //logger.info("【推送消息】开始执行：{}", DateUtil.formatDateTime(new Date()));
        String sendMsg = "服务器定时推送任务 查询数据, 返回给前端 "+ System.currentTimeMillis();
        wsTemplate.convertAndSend(ConstantForSend.PUSH_SERVER, sendMsg);
        //logger.info("【推送消息】执行结束：{}", DateUtil.formatDateTime(new Date()));
    }

    /**
     * 数据中心缓冲表, 轮询发送列表
     * 上一次执行完毕时间点之后5秒再执行
     */
//    @Async("doSomethingExecutor")
//    @Scheduled(fixedDelay = 5000)
    public void msgPolling(){

    }

    /**
     * 在凌晨00:01运行, 刷新应用key的发送量
     */
//    @Async("doSomethingExecutor")
//    @Scheduled(cron= "0 0 1 * * ?")
    public void dailyRefreshKeySend(){

    }


}
