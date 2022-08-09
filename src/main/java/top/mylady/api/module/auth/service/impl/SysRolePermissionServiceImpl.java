package top.mylady.api.module.auth.service.impl;

import top.mylady.api.module.auth.entity.SysRolePermissionEntity;
import top.mylady.api.module.auth.mapper.SysRolePermissionMapper;
import top.mylady.api.module.auth.service.SysRolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色-权限关联表 服务实现类
 * </p>
 *
 * @author mylady
 * @since 2022-06-06
 */
@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermissionEntity> implements SysRolePermissionService {

}
