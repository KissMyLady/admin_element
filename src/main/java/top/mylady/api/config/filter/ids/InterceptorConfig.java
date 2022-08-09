package top.mylady.api.config.filter.ids;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 拦截器统一配置中心
 *
 * springboot 2.0配置WebMvcConfigurationSupport之后，会导致默认配置被覆盖，要访问静态资源需要重写addResourceHandlers方法
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties("ignored")
public class InterceptorConfig implements WebMvcConfigurer {

    /**
     * 忽略的地址，不拦截的地址
     */
    List<String> urls;


    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new LoginInterceptor());

        // 排除不需要拦截的路径
        interceptorRegistration.excludePathPatterns(urls);

        // 需要拦截的路径
        interceptorRegistration.addPathPatterns("/**");

        WebMvcConfigurer.super.addInterceptors(registry);
    }

}
