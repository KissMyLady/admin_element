package top.mylady.api.config.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/7/15 22:28
 * @Description: 启动失败
 */
@Component
public class AppRunFailed implements ApplicationListener<ApplicationFailedEvent>{

    private static final Logger logger = LoggerFactory.getLogger(AppRunFailed.class);

    @Override
    public void onApplicationEvent(ApplicationFailedEvent event) {
        StartLog.getInstance().errorPrint();
        logger.error("项目启动失败, 原因是: {}", event.getException().toString());
    }
}
