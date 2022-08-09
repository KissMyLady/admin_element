package top.mylady.api.controller.app.authApp;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.mylady.api.config.annotation.LogsAop;
import top.mylady.api.config.annotation.RequiresPermissions;
import top.mylady.api.config.ctrlConfig.ResultVo;
import top.mylady.api.dto.app.authApp.SysPermissionDto;
import top.mylady.api.module.auth.entity.SysPermissionEntity;
import top.mylady.api.service.app.authApp.impl.AuthSysPermissionServiceImpl;
import javax.annotation.Resource;


/**
 * @Author: KissMyLady
 * @Date: Created in 2022/7/29 8:04
 * @Description:
 */
@RestController
@RequestMapping("/sysPermissionCtrl")
public class SysPermissionCtrl {

    @Resource
    private AuthSysPermissionServiceImpl sysPermissionService;

    /**
     * sys_permission 表的增删改查
     */
    @LogsAop(title="sys_permission权限表数据 添加")
    @RequiresPermissions("sys_permission:add")
    @PostMapping("/sys_permission/add")
    ResultVo<?> sys_permissionAdd(@RequestBody SysPermissionEntity dto){
        return sysPermissionService.add(dto);
    }

    @LogsAop(title="sys_permission权限表数据 删除")
    @RequiresPermissions("sys_permission:delete")
    @PostMapping("/sys_permission/delete")
    ResultVo<?> sys_permissionDelete(@RequestBody SysPermissionDto dto){
        return sysPermissionService.delete(dto);
    }

    @LogsAop(title="sys_permission权限表数据 修改")
    @RequiresPermissions("sys_permission:change")
    @PostMapping("/sys_permission/change")
    ResultVo<?> sys_permissionChange(@RequestBody SysPermissionEntity dto){
        return sysPermissionService.change(dto);
    }

    @RequiresPermissions("sys_permission:list")
    @PostMapping("/sys_permission/list")
    ResultVo<?> sys_permissionList(@RequestBody SysPermissionDto dto){
        return sysPermissionService.list(dto);
    }


}
