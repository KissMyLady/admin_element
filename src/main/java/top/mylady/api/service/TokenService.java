package top.mylady.api.service;

import com.github.benmanes.caffeine.cache.Cache;
import top.mylady.api.config.exception.CommonJsonException;
import top.mylady.api.config.exception.ServiceException;
import top.mylady.api.dto.login.DfUserModel;
import top.mylady.api.dto.session.SessionUserInfo;
import top.mylady.api.module.auth.mapper.SysUserMapper;
import top.mylady.api.util.JWT.GeneratorToken;
import top.mylady.api.util.StringTools;
import top.mylady.api.util.constants.ErrorEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class TokenService {

    private static final Logger logger = LoggerFactory.getLogger(TokenService.class);

    @Resource
    Cache<String, SessionUserInfo> cacheMap;

    @Resource
    SysUserMapper loginMapper;

    /**
     * 用户登录验证通过后(sso/帐密),生成token,记录用户已登录的状态
     */
    public String generateToken(String username) {

        //上下文设置username
        MDC.put("username", username);
        //String username1 = MDC.get("username");
        //logger.info("MDC存储了username1, 现在取出: {}", username1);

        //String token = UUID.randomUUID().toString().replace("-", "").substring(0, 20);

        //JWT 生成token
        DfUserModel userModel = new DfUserModel(username, username);
        GeneratorToken generatorToken = new GeneratorToken();
        String jwtToken = generatorToken.generateToken(userModel);

        if(jwtToken == null){
            throw new ServiceException(500, "用户登录错误, 请稍后再试");
        }
        //设置用户信息缓存
        setCache(jwtToken, username);
        return jwtToken;
    }

    public SessionUserInfo getUserByToken(String token){
        return getUserInfoFromCache(token);
    }

    public SessionUserInfo getUserInfo() {
        String token = MDC.get("token");
        return getUserInfoFromCache(token);
    }

    /**
     * 根据token查询用户信息
     * 如果token无效,会抛未登录的异常
     */
    public SessionUserInfo getUserInfoFromCache(String token) {
        if (StringTools.isNullOrEmpty(token)) {
            throw new CommonJsonException(ErrorEnum.E_20011);
        }
        SessionUserInfo info = cacheMap.getIfPresent(token);
        // logger.info("用户信息获取打印: info: {}", info);
        if (info == null) {
            logger.error("如果token无效, 当前没拿到缓存 token={}", token);
            throw new CommonJsonException(ErrorEnum.E_20011);
        }
        return info;
    }

    private void setCache(String token, String username) {
        //查询用户权限
        SessionUserInfo info = getUserInfoByUsername(username);
        //logger.info("设置用户信息缓存:token={} , username={}, info={}", token, username, info);
        // logger.info("setCache info: {}", info);

        cacheMap.put(token, info);
    }

    ///删除
    private void deleteCache(String key){
        try {
            cacheMap.invalidate(key);
            logger.info("缓存字段: {}, 移除成功", key);
        }
        catch (Exception e){
            logger.error("删除: {} 缓存字段失败, 原因是: {}", key, ""+e);
        }
    }

    //获取
    private Object get(String key){
        return cacheMap.getIfPresent(key);
    }

    /**
     * 退出登录时,将token置为无效
     */
    public void invalidateToken() {
        String token = MDC.get("token");
        if (!StringTools.isNullOrEmpty(token)) {
            cacheMap.invalidate(token);
        }
        logger.debug("退出登录,清除缓存:token={}", token);
    }

    private SessionUserInfo getUserInfoByUsername(String username) {
        //查询用户, 根据用户名称, 查到用户的菜单列表, 权限列表
        SessionUserInfo userInfo = loginMapper.getUserInfo(username);

        //如果有权限角色
        if (userInfo.getRoleIds().contains(1)) {
            //管理员,查出全部按钮和权限码
            userInfo.setMenuList(loginMapper.getAllMenu());
            userInfo.setPermissionList(loginMapper.getAllPermissionCode());
        }
        return userInfo;
    }
}
