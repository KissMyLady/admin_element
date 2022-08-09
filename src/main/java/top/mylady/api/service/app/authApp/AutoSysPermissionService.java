package top.mylady.api.service.app.authApp;

import top.mylady.api.config.ctrlConfig.ResultVo;
import top.mylady.api.dto.app.authApp.SysPermissionDto;
import top.mylady.api.module.auth.entity.SysPermissionEntity;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/7/29 8:05
 * @Description:
 */
public interface AutoSysPermissionService {

    ResultVo<?> add(SysPermissionEntity dto);
    ResultVo<?> delete(SysPermissionDto dto);
    ResultVo<?> change(SysPermissionEntity dto);
    ResultVo<?> list(SysPermissionDto dto);

}
