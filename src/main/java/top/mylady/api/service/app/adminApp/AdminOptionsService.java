package top.mylady.api.service.app.adminApp;


import org.springframework.web.multipart.MultipartFile;
import top.mylady.api.config.ctrlConfig.ResultVo;
import top.mylady.api.dto.app.adminApp.SysFileDto;
import top.mylady.api.dto.app.adminApp.SysOptionDto;
import top.mylady.api.module.sys_manage.entity.SysOptionsEntity;


public interface AdminOptionsService {

    ResultVo<?> add(SysOptionsEntity dto);
    ResultVo<?> delete(SysOptionDto dto);
    ResultVo<?> change(SysOptionsEntity dto);
    ResultVo<?> queryList(SysOptionDto dto);



}
