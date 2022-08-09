package top.mylady.api.module.files.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.mylady.api.module.files.entity.FileAttachmentEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 系统附件表 Mapper 接口
 * </p>
 *
 * @author mylady
 * @since 2022-07-28
 */
@Mapper
public interface FileAttachmentMapper extends BaseMapper<FileAttachmentEntity> {

    void changeFileType(@Param("id") String id,
                        @Param("typeId") String typeId);

}
