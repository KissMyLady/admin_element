package top.mylady.api.controller;

import com.alibaba.fastjson.JSONObject;
import top.mylady.api.config.annotation.LogsAop;
import top.mylady.api.config.exception.CommonJsonException;
import top.mylady.api.dto.login.OptionDto;
import top.mylady.api.module.sys_manage.entity.SysLogsEntity;
import top.mylady.api.module.sys_manage.service.impl.SysLogsServiceImpl;
import top.mylady.api.service.LoginService;
import top.mylady.api.service.utilsService.impl.UtilsLogServiceImpl;
import top.mylady.api.util.CommonUtil;
import top.mylady.api.util.constants.ConstantForSend;
import top.mylady.api.util.constants.ErrorEnum;
import top.mylady.api.util.myUtils.IpUtil;
import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: heeexy
 * @description: 登录相关Controller
 * @date: 2017/10/24 10:33
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @Resource
    private SysLogsServiceImpl sysLogsService;

    @Resource
    private UtilsLogServiceImpl utilsLogService;

    /**
     * 登录
     */
    @PostMapping("/auth")
    //@LogsAop(title="登录后获取用户信息")
    public JSONObject authLogin(@RequestBody JSONObject requestJson,
                                HttpServletRequest request
                                ) {
        String header = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(header);

        String ip = IpUtil.getIpAddr(request);
        //获取id地址的ZH-CN
        String localCityInfo = utilsLogService.getLocalCityInfo(ip);

        String url = request.getRequestURL().toString();
        String httpMethod = request.getMethod();
        String browser = userAgent.getBrowser().toString();
        String systemName = userAgent.getOperatingSystem().toString();

        //保存
        SysLogsEntity entity = new SysLogsEntity();
        entity.setLogType(ConstantForSend.lv_info);
        entity.setLogTitle("登录日志");   //日志标题

        entity.setReqIp(ip);                  //操作IP
        entity.setReqAddress(localCityInfo);  //IP地址
        entity.setReqAgent(header);           //请求头
        entity.setReqBrowser(browser);        //浏览器
        entity.setReqSystem(systemName);      //操作系统
        entity.setReqUrl(url);                //url
        entity.setReqMethod(httpMethod);      //请求方法

        String username = requestJson.get("username").toString();
        entity.setCreateBy(username);

        Map<String, String> saveData = new HashMap<>();
        saveData.put("username", username);
        saveData.put("password", "");

        entity.setReqParams(saveData.toString());  //操作提交的数据
        try {
            sysLogsService.save(entity);
        } catch (Exception e) {
            logger.error("保存AOP日志失败, 原因是: {}, {}", "" + e, entity);
        }

        //校验
        CommonUtil.hasAllRequired(requestJson, "username,password");
        return loginService.authLogin(requestJson);
    }

    /**
     * 查询当前登录用户的信息
     */
    @PostMapping("/getInfo")
    public JSONObject getInfo(@RequestBody OptionDto dto) {
        if(dto.getToken() == null || dto.getToken().equals("")){
            throw new CommonJsonException(ErrorEnum.E_20011);
        }
        return loginService.getInfo(dto);
    }

    /**
     * 登出
     */
    @PostMapping("/logout")
    @LogsAop(title="登出")
    public JSONObject logout() {
        return loginService.logout();
    }
}
