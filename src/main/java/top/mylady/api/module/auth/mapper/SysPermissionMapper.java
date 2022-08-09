package top.mylady.api.module.auth.mapper;

import top.mylady.api.module.auth.entity.SysPermissionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 后台权限表 Mapper 接口
 * </p>
 *
 * @author mylady
 * @since 2022-06-06
 */
@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermissionEntity> {

}
