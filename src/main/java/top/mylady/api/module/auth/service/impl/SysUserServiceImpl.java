package top.mylady.api.module.auth.service.impl;

import top.mylady.api.module.auth.entity.SysUserEntity;
import top.mylady.api.module.auth.mapper.SysUserMapper;
import top.mylady.api.module.auth.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author mylady
 * @since 2022-06-06
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements SysUserService {

}
