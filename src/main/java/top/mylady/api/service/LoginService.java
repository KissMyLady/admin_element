package top.mylady.api.service;

import com.alibaba.fastjson.JSONObject;
import top.mylady.api.config.exception.CommonJsonException;
import top.mylady.api.dto.login.OptionDto;
import top.mylady.api.module.auth.mapper.SysUserMapper;
import top.mylady.api.dto.session.SessionUserInfo;
import top.mylady.api.util.CommonUtil;
import top.mylady.api.util.constants.ErrorEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author: heeexy
 * @description: 登录service实现类
 * @date: 2017/10/24 11:53
 */
@Service
public class LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    /**
     * 注入bean
     */
    @Resource
    private SysUserMapper loginMapper;

    @Autowired
    private TokenService tokenService;

    /**
     * 用户登录, 表单提交
     */
    public JSONObject authLogin(JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");

        JSONObject res = new JSONObject();

        try {
            //查询用户
            Map<String, Object> user = loginMapper.checkUser(username, password);
            if (user == null) {
                throw new CommonJsonException(ErrorEnum.E_10010);
            }

            //通过用户生成token
            String token = tokenService.generateToken(username);
            res.put("token", token);
            return CommonUtil.successJson(res);
        }
        catch (Exception e){
            logger.error("查询用户失败, 原因是: {}", ""+e);
        }
        return null;
    }

    /**
     * 查询当前登录用户的权限等信息
     */
    public JSONObject getInfo() {
        //从session获取用户信息
        SessionUserInfo userInfo = tokenService.getUserInfo();
        return CommonUtil.successJson(userInfo);
    }

    public JSONObject getInfo(OptionDto dto) {
        //从session获取用户信息
        SessionUserInfo userInfo = tokenService.getUserByToken(dto.getToken());
        //logger.info("获取用户信息了: userInfo: {}", userInfo);
        return CommonUtil.successJson(userInfo);
    }

    /**
     * 退出登录
     */
    public JSONObject logout() {
        tokenService.invalidateToken();
        return CommonUtil.successJson();
    }
}
