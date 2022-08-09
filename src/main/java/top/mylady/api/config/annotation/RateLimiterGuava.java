package top.mylady.api.config.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/6/18 9:15
 * @Description: 限流注解，添加了 {@link AliasFor} 必须通过 {@link AnnotationUtils} 获取，才会生效
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiterGuava {

    int NOT_LIMITED = 0;

    //qps
    @AliasFor("qps") double value() default NOT_LIMITED;

    //qps
    @AliasFor("value") double qps() default NOT_LIMITED;

    //超时时长
    int timeout() default 0;

    //超时单位
    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;

}
