package top.mylady.api.module.sys_manage.mapper;

import top.mylady.api.dto.sysDto.SysLogDto;
import top.mylady.api.module.sys_manage.entity.SysLogsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 日志表 Mapper 接口
 * </p>
 *
 * @author mylady
 * @since 2022-06-15
 */
@Mapper
public interface SysLogsMapper extends BaseMapper<SysLogsEntity> {

    //删除 ? 天 前的日志记录
    boolean deleteLogs(@Param("days") int days);

    //sql分页
    List<Map<String, Object>> queryLogs(SysLogDto dto);

    //查询日志总数
    int queryTotal();

}
