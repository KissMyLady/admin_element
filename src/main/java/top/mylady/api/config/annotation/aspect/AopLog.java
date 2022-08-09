package top.mylady.api.config.annotation.aspect;

import cn.hutool.core.util.ArrayUtil;
import com.google.common.collect.Maps;
import top.mylady.api.config.annotation.LogsAop;
import top.mylady.api.config.exception.CommonJsonException;
import top.mylady.api.dto.session.SessionUserInfo;
import top.mylady.api.module.sys_manage.entity.SysLogsEntity;
import top.mylady.api.module.sys_manage.service.impl.SysLogsServiceImpl;
import top.mylady.api.service.TokenService;
import top.mylady.api.service.utilsService.impl.UtilsLogServiceImpl;
import top.mylady.api.util.StringTools;
import top.mylady.api.util.constants.ErrorEnum;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.mylady.api.util.myUtils.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Objects;


/**
 * @Author: KissMyLady
 * @Date: Created in 2022/6/20 11:09
 * @Description: 使用 aop 切面记录请求日志信息
 */
@Aspect
@Component
public class AopLog {

    private static final Logger logger = LoggerFactory.getLogger(AopLog.class);

    @Resource
    private SysLogsServiceImpl sysLogsService;

    @Resource
    private UtilsLogServiceImpl utilsLogService;

    @Autowired
    TokenService tokenService;

    private String title;

    private void setTitle(String title){
        this.title = title;
    }
    private String getTitle(){
        return title;
    }

//    @Pointcut("execution(public * com.heeexy.example.controller.*.*(..))")
//    public void log() {
//    }

    @Pointcut("@annotation(top.mylady.api.config.annotation.LogsAop)")
    public void log() {
    }

    /**
     * 环绕操作
     *
     * @param point 切入点
     * @return 原方法返回值
     * @throws Throwable 异常信息
     */
    @Around("log()")
    public Object aroundLog(ProceedingJoinPoint point) throws Throwable {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

        SysLogsEntity entity = new SysLogsEntity();

        //获取用户信息
        //通过token解析出username
        String token = request.getHeader("token");
        if(token == null || "".equals(token)){
            entity.setCreateBy("未登录");
        }else {
            try {
                SessionUserInfo info = tokenService.getUserInfoFromCache(token);
                if (info != null) {
                    String username = info.getUsername();
                    String nickname = info.getNickname();
                    entity.setCreateBy(nickname+ "-"+ username);
                }else {
                    entity.setCreateBy("查询不到用户");
                }
            } catch (CommonJsonException e) {
                logger.warn("登录了, 但是是token无效:{}", token);
                throw new CommonJsonException(ErrorEnum.E_20011);
            }
        }

        // 打印请求相关参数
        long startTime = System.currentTimeMillis();
        Object result = point.proceed();

        String header = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(header);

        String ip = getIp(request);
        String url = request.getRequestURL().toString();
        //String classMethod = String.format("%s.%s", point.getSignature().getDeclaringTypeName(), point.getSignature().getName());
        String httpMethod = request.getMethod();
        Map<String, Object> reqParams = getNameAndValue(point);
        Long ss = System.currentTimeMillis() - startTime;
        String browser = userAgent.getBrowser().toString();
        String os = userAgent.getOperatingSystem().toString();

        entity.setLogType("AOP");
        entity.setLogTitle(this.title);  //日志标题
        //获取id地址的ZH-CN
        String localCityInfo = utilsLogService.getLocalCityInfo(ip);

        entity.setReqIp(ip);                  //操作IP
        entity.setReqAddress(localCityInfo);  //IP地址
        entity.setReqAgent(header);           //请求头
        entity.setReqBrowser(browser);        //浏览器
        entity.setReqSystem(os);              //操作系统
        entity.setReqUrl(url);                //url
        entity.setReqMethod(httpMethod);      //请求方法
        entity.setTimeOut(ss);                //耗时
        entity.setReqParams(reqParams.toString());  //操作提交的数据
        try {
            sysLogsService.save(entity);
        }
        catch (Exception e){
            logger.error("保存AOP日志失败, 原因是: {}, {}", ""+e, entity);
        }
        return result;
    }

    /**
     *  获取方法参数名和参数值
     * @param joinPoint
     * @return
     */
    private Map<String, Object> getNameAndValue(ProceedingJoinPoint joinPoint) {

        final Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;

        Method method = ((MethodSignature) signature).getMethod();
        LogsAop logsAop = method.getAnnotation(LogsAop.class);
        this.title = logsAop.title();

        final String[] names = methodSignature.getParameterNames();
        final Object[] args = joinPoint.getArgs();

        if (ArrayUtil.isEmpty(names) || ArrayUtil.isEmpty(args)) {
            return Collections.emptyMap();
        }
        if (names.length != args.length) {
            logger.warn("{}方法参数名和参数值数量不一致", methodSignature.getName());
            return Collections.emptyMap();
        }
        Map<String, Object> map = Maps.newHashMap();
        for (int i = 0; i < names.length; i++) {
            map.put(names[i], args[i]);
        }
        return map;
    }

    private static final String UNKNOWN = "unknown";

    /**
     * 获取ip地址
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        String comma = ",";
        String localhost = "127.0.0.1";
        if (ip.contains(comma)) {
            ip = ip.split(",")[0];
        }
        if (localhost.equals(ip)) {
            // 获取本机真正的ip地址
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return ip;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class Log {

        //标题
        private String title;
        // 线程id
        private String threadId;
        // 线程名称
        private String threadName;
        // ip
        private String ip;
        // url
        private String url;
        // http方法 GET POST PUT DELETE PATCH
        private String httpMethod;
        // 类方法
        private String classMethod;
        // 请求参数
        private Object requestParams;
        // 返回参数
        private Object result;
        // 接口耗时
        private Long timeCost;
        // 操作系统
        private String os;
        // 浏览器
        private String browser;
        // user-agent
        private String userAgent;
    }


}
