package top.mylady.api.config.filter.aop;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.mylady.api.config.annotation.Logical;
import top.mylady.api.config.annotation.RequiresPermissions;
import top.mylady.api.config.exception.UnauthorizedException;
import top.mylady.api.dto.session.SessionUserInfo;
import top.mylady.api.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

/**
 * @author heeexy
 * @description: [角色权限]控制拦截器
 */
@Aspect
@Component
@Order(3)
public class PermissionAspect {

    private static final Logger logger = LoggerFactory.getLogger(PermissionAspect.class);

    @Autowired
    TokenService tokenService;

    @Before("@annotation(top.mylady.api.config.annotation.RequiresPermissions)")
    public void before(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

        //先从request header获取token,
        String token = request.getHeader("token");
        SessionUserInfo userInfo;

        if(null != token && !token.equals("")){
            userInfo = tokenService.getUserInfoFromCache(token);
        }else {
            userInfo = tokenService.getUserInfo();
        }
        Set<String> myCodes = userInfo.getPermissionList();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        RequiresPermissions a = methodSignature.getMethod().getAnnotation(RequiresPermissions.class);
        String[] perms = a.value();

        //logger.debug("校验权限code: {}", Arrays.toString(perms));
        //logger.debug("用户已有权限: {}", myCodes);

        //5.对比[要求]的code和[用户实际拥有]的code
        if (a.logical() == Logical.AND) {
            //必须包含要求的每个权限
            for (String perm : perms) {
                if (!myCodes.contains(perm)) {
                    logger.warn("用户缺少权限 code : {}", perm);
                    throw new UnauthorizedException();//抛出[权限不足]的异常
                }
            }
        } else {
            //多个权限只需包含其中一种即可
            boolean flag = false;
            for (String perm : perms) {
                if (myCodes.contains(perm)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                logger.warn("用户缺少权限 code= : {} (任意有一种即可)", Arrays.toString(perms));
                throw new UnauthorizedException();//抛出[权限不足]的异常
            }
        }
    }
}
