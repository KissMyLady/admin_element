package top.mylady.api.config.listener;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * @Author: KissMyLady
 * @Date: Created in 2022/7/15 22:30
 * @Description:
 */
@Configuration
@Data
@EqualsAndHashCode(callSuper = false)
public class GlobalProperties {

    /** 系统名称 */
    private String systemName = "Shiro-Application";

    /** 系统启动时间 为空则默认 真实当前系统启动时间 */
    private String systemStarterTime;

    /** 是否开启演示模式 */
    private boolean enableDemo;

}
