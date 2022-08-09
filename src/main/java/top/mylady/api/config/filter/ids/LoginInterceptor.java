package top.mylady.api.config.filter.ids;

import cn.hutool.core.util.ObjectUtil;
import org.slf4j.MDC;
import top.mylady.api.config.exception.CommonJsonException;
import top.mylady.api.dto.login.DfUserModel;
import top.mylady.api.util.JWT.GeneratorToken;
import top.mylady.api.util.StringTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object o) throws Exception {
        //通过token解析出username
        String token = request.getHeader("token");
        if (!StringTools.isNullOrEmpty(token)) {
            try {
                GeneratorToken generatorToken = new GeneratorToken();
                DfUserModel userInfo = generatorToken.getUserInfo(token);
                if(ObjectUtil.isNotNull(userInfo)){
                    return true;
                }else {
                    String url = "/marry/hello";
                    response.sendRedirect(request.getContextPath() + url);
                    return false;
                }
            } catch (CommonJsonException e) {
                String url = "/marry/hello";
                response.sendRedirect(request.getContextPath() + url);
                return false;
            }
        }
        // 跳转到登录页
        String url = "/marry/hello";
        response.sendRedirect(request.getContextPath() + url);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        //logger.info("postHandle 处理进程");
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception e) throws Exception {
        //logger.info("afterCompletion 处理进程");
    }
}
