package top.mylady.api.config.exception;

import com.alibaba.fastjson.JSONObject;
import top.mylady.api.config.ctrlConfig.ResultVo;
import top.mylady.api.util.CommonUtil;
import top.mylady.api.util.constants.ErrorEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: heeexy
 * @description: 统一异常拦截
 * @date: 2017/10/24 10:31
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    /**
     * 拦截 业务异常
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public ResultVo<?> businessException(ServiceException e) {
        logger.warn("拦截, 业务异常 - 异常编号：{} - 异常信息：{}", e.getCode(), e.getMessage());
        Map<String ,Object> map = new HashMap<>();
        map.put("msg", e.getMessage());
        map.put("code", e.getCode());
        map.put("error", e.getErrorMessage());
        map.put("cause", e.getCause());
        //map.put("trace", e.getStackTrace());
        return ResultVo.error(406, "全局错误拦截: "+e.getMessage(), map);
    }

    //其他所有异常
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultVo exceptionHandler(HttpServletRequest req, Exception e) {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", e.getMessage());
        map.put("cause", e.getCause());
        //map.put("trace", e.getStackTrace());
        map.put("reqUrl", req.getContextPath());
        map.put("method", req.getMethod());
        return ResultVo.error(405, "异常拦截: "+e.getMessage(), map);
    }

//    @ExceptionHandler(value = Exception.class)
//    public JSONObject defaultErrorHandler(HttpServletRequest req, Exception e) {
//        String errorPosition = "";
//        //如果错误堆栈信息存在
//        if (e.getStackTrace().length > 0) {
//            StackTraceElement element = e.getStackTrace()[0];
//            String fileName = element.getFileName() == null ? "未找到错误文件" : element.getFileName();
//            int lineNumber = element.getLineNumber();
//            errorPosition = fileName + ":" + lineNumber;
//        }
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("code", ErrorEnum.E_400.getErrorCode());
//        jsonObject.put("msg", ErrorEnum.E_400.getErrorMsg());
//        JSONObject errorObject = new JSONObject();
//        errorObject.put("errorLocation", e + "    错误位置:" + errorPosition);
//        jsonObject.put("info", errorObject);
//        logger.error("异常", e);
//        return jsonObject;
//    }

    /**
     * GET/POST请求方法错误的拦截器
     * 因为开发时可能比较常见,而且发生在进入controller之前,上面的拦截器拦截不到这个错误
     * 所以定义了这个拦截器
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public JSONObject httpRequestMethodHandler() {
        return CommonUtil.errorJson(ErrorEnum.E_500);
    }

    /**
     * 本系统自定义错误的拦截器
     * 拦截到此错误之后,就返回这个类里面的json给前端
     * 常见使用场景是参数校验失败,抛出此错,返回错误信息给前端
     */
    @ExceptionHandler(CommonJsonException.class)
    public JSONObject commonJsonExceptionHandler(CommonJsonException commonJsonException) {
        return commonJsonException.getResultJson();
    }

    /**
     * 权限不足报错拦截
     */
    @ExceptionHandler(UnauthorizedException.class)
    public JSONObject unauthorizedExceptionHandler() {
        return CommonUtil.errorJson(ErrorEnum.E_502);
    }

    /**
     * 未登录报错拦截
     * 在请求需要权限的接口,而连登录都还没登录的时候,会报此错
     */
    @ExceptionHandler(UnauthenticatedException.class)
    public JSONObject unauthenticatedException() {
        return CommonUtil.errorJson(ErrorEnum.E_20011);
    }
}
