package top.mylady.api.config.system;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.Executors;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/6/10 10:00
 * @Description: 多任务配置类, 参考 https://blog.csdn.net/qq_40841531/article/details/111054058
 */
@Component
public class ScheduleConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        Method[] methods = BatchProperties.Job.class.getMethods();
        int defaultPoolSize = 2;
        int corePoolSize = 0;

        if (!CollectionUtils.isEmpty(Arrays.asList(methods))) {

            for (Method method : methods) {

                Scheduled annotation = method.getAnnotation(Scheduled.class);

                if (annotation != null) {

                    corePoolSize++;
                }
            }
            if (defaultPoolSize > corePoolSize) {

                corePoolSize = defaultPoolSize;
            }

            scheduledTaskRegistrar.setScheduler(Executors.newScheduledThreadPool(corePoolSize));
        }
    }
}
