package top.mylady.api.config.listener;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/7/15 22:29
 * @Description: 启动成功监听
 */
@Component
public class AppRunSuccess implements ApplicationListener<ApplicationReadyEvent>{

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        event.getApplicationContext();
        // 输出启动日志
        StartLog.getInstance().successPrint();
    }
}
