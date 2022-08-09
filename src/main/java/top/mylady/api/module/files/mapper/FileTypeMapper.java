package top.mylady.api.module.files.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.mylady.api.module.files.entity.FileTypeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 文件模块类型 Mapper 接口
 * </p>
 *
 * @author mylady
 * @since 2022-07-28
 */
@Mapper
public interface FileTypeMapper extends BaseMapper<FileTypeEntity> {

}
