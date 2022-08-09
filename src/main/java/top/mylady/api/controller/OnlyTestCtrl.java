package top.mylady.api.controller;

import top.mylady.api.config.annotation.RateLimiter;
import top.mylady.api.config.annotation.RateLimiterGuava;
import top.mylady.api.config.ctrlConfig.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


/**
 * @Author: KissMyLady
 * @Date: Created in 2022/6/18 8:04
 * @Description:
 */
@RestController
@RequestMapping("/test")
public class OnlyTestCtrl {

    private static final Logger logger = LoggerFactory.getLogger(OnlyTestCtrl.class);

    @GetMapping("webSock")
    @RateLimiterGuava(value = 1.0, timeout = 300)
    public ResultVo<?> webScok() {
        return ResultVo.success("请求成功");
    }

    /**
     * Guava版限流器
     */
    @GetMapping("limitGuava")
    @RateLimiterGuava(value = 1.0, timeout = 300)
    public ResultVo<?> limitGuava() {
        return ResultVo.success("请求成功");
    }

    /**
     * 测试限流注解, 10 次
     */
    @GetMapping("limitTest")
    @RateLimiter(value = 5)
    public ResultVo<?> limitTest() {
        return ResultVo.success("请求成功");
    }

    @GetMapping("t2")
    @RateLimiter(value = 2, key = "测试自定义key")
    public ResultVo<?> limitTest2() {
        return ResultVo.success("测试自定义key");
    }

    //获取错误html页面
    @GetMapping("error")
    public ModelAndView getErrorPage() {
        logger.error("IDS登录, request获取不到ticket, 返回抛出异常");
        ModelAndView modelAndView = new ModelAndView("/error/loginError");
        return modelAndView;
        //return new ModelAndView("redirect:"+ "/error/loginError.html");
    }

}
