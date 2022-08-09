package top.mylady.api.module.sys_manage.mapper;

import top.mylady.api.module.sys_manage.entity.SysOptionsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mylady
 * @since 2022-06-15
 */
@Mapper
public interface SysOptionsMapper extends BaseMapper<SysOptionsEntity> {


    List<Map<String, Object>> queryMapPageDesc();


}
