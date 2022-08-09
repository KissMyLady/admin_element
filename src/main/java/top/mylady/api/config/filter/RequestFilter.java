package top.mylady.api.config.filter;

import top.mylady.api.config.exception.CommonJsonException;
import top.mylady.api.dto.session.SessionUserInfo;
import top.mylady.api.service.TokenService;
import top.mylady.api.util.StringTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @author heeexy
 */
//@Component
public class RequestFilter extends OncePerRequestFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(RequestFilter.class);

    @Autowired
    TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            //每个请求记录一个traceId,可以根据traceId搜索出本次请求的全部相关日志
            MDC.put("traceId", UUID.randomUUID().toString().replace("-", "").substring(0, 12));
            setUsername(request);
            setProductId(request);
            //使request中的body可以重复读取 https://juejin.im/post/6858037733776949262#heading-4
            request = new ContentCachingRequestWrapper(request);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            // throw e;
            logger.error("doFilterInternal 过滤器错误, 原因是: {}", e+"");
        } finally {
            //清理ThreadLocal
            MDC.clear();
        }
    }

    /**
     * 将url参数中的productId放入ThreadLocal
     */
    private void setProductId(HttpServletRequest request) {
        String productIdStr = request.getParameter("productId");
        if (!StringTools.isNullOrEmpty(productIdStr)) {
            //log.debug("url中productId = {}", productIdStr);
            MDC.put("productId", productIdStr);
        }
    }

    private void setUsername(HttpServletRequest request) {
        //通过token解析出username
        String token = request.getHeader("token");

        if (!StringTools.isNullOrEmpty(token)) {
            MDC.put("token", token);
            logger.info("请求中获取到的token打印: {}", token);
            try {
                SessionUserInfo info = tokenService.getUserInfo();
                if (info != null) {
                    String username = info.getUsername();
                    MDC.put("username", username);
                }
            } catch (CommonJsonException e) {
                logger.info("无效的token:{}", token);
            }
        }
    }
}
