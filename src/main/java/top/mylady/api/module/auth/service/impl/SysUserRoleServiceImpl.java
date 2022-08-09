package top.mylady.api.module.auth.service.impl;

import top.mylady.api.module.auth.entity.SysUserRoleEntity;
import top.mylady.api.module.auth.mapper.SysUserRoleMapper;
import top.mylady.api.module.auth.service.SysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户-角色关联表 服务实现类
 * </p>
 *
 * @author mylady
 * @since 2022-06-06
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRoleEntity> implements SysUserRoleService {

}
