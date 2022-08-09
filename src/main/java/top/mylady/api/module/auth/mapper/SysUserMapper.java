package top.mylady.api.module.auth.mapper;

import top.mylady.api.dto.session.SessionUserInfo;
import top.mylady.api.module.auth.entity.SysUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author mylady
 * @since 2022-06-06
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUserEntity> {

    //根据用户名和密码查询对应的用户
    Map<String, Object> checkUser(@Param("username") String username,
                                  @Param("password") String password);

    SessionUserInfo getUserInfo(String username);

    Set<String> getAllMenu();

    Set<String> getAllPermissionCode();

}
