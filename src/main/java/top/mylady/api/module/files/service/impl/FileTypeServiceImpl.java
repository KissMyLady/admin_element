package top.mylady.api.module.files.service.impl;

import top.mylady.api.module.files.entity.FileTypeEntity;
import top.mylady.api.module.files.mapper.FileTypeMapper;
import top.mylady.api.module.files.service.FileTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文件模块类型 服务实现类
 * </p>
 *
 * @author mylady
 * @since 2022-07-28
 */
@Service
public class FileTypeServiceImpl extends ServiceImpl<FileTypeMapper, FileTypeEntity> implements FileTypeService {

}
