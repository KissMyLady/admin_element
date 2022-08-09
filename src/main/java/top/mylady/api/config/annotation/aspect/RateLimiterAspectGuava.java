package top.mylady.api.config.annotation.aspect;

import com.google.common.util.concurrent.RateLimiter;
import top.mylady.api.config.annotation.RateLimiterGuava;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/6/18 9:16
 * @Description:
 */
@Aspect
@Component
public class RateLimiterAspectGuava {

    private static final Logger logger = LoggerFactory.getLogger(RateLimiterAspectGuava.class);

    private static final ConcurrentMap<String, com.google.common.util.concurrent.RateLimiter> RATE_LIMITER_CACHE = new ConcurrentHashMap<>();

    @Pointcut("@annotation(top.mylady.api.config.annotation.RateLimiterGuava)")
    public void rateLimit() {

    }

    @Around("rateLimit()")
    public Object pointcut(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        String methodName = method.getName();

        // 通过 AnnotationUtils.findAnnotation 获取 RateLimiter 注解
        RateLimiterGuava rateLimiter = AnnotationUtils.findAnnotation(method, RateLimiterGuava.class);

        if (rateLimiter != null && rateLimiter.qps() > RateLimiterGuava.NOT_LIMITED) {
            double qps = rateLimiter.qps();
            RateLimiter rateLimiter2 = RATE_LIMITER_CACHE.get(method.getName());
            if (rateLimiter2 == null) {
                // 初始化 QPS
                RATE_LIMITER_CACHE.put(methodName, com.google.common.util.concurrent.RateLimiter.create(qps));
            }

            logger.debug("【{}】的QPS设置为: {}", method.getName(), RATE_LIMITER_CACHE.get(methodName).getRate());

            // 尝试获取令牌
            RateLimiter rateLimiter1 = RATE_LIMITER_CACHE.get(methodName);
            boolean b = RATE_LIMITER_CACHE.get(methodName).tryAcquire(rateLimiter.timeout(), rateLimiter.timeUnit());

            if (rateLimiter1 != null && !b) {
                throw new RuntimeException("手速太快了，慢点儿吧~");
            }
        }
        return point.proceed();
    }

}
