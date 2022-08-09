package top.mylady.api.controller.app.adminApp.sysCtrl;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.mylady.api.config.annotation.RequiresPermissions;
import top.mylady.api.config.ctrlConfig.ResultVo;
import top.mylady.api.dto.app.adminApp.SysOptionDto;
import top.mylady.api.module.sys_manage.entity.SysOptionsEntity;
import top.mylady.api.service.app.adminApp.impl.AdminOptionsServiceImpl;
import javax.annotation.Resource;


@RestController
@RequestMapping("/admin")
public class adminCtrl {

    @Resource
    private AdminOptionsServiceImpl adminOptionsService;

    @PostMapping("/hello")
    ResultVo<?> hello() {
        return ResultVo.success("Hello");
    }


    //系统操作码
    @RequiresPermissions("option:add")
    @PostMapping("/option/add")
    ResultVo<?> add(@RequestBody SysOptionsEntity dto) {
        return adminOptionsService.add(dto);
    }

    @RequiresPermissions("option:delete")
    @PostMapping("/option/delete")
    ResultVo<?> delete(@RequestBody SysOptionDto dto) {
        return adminOptionsService.delete(dto);
    }

    @RequiresPermissions("option:change")
    @PostMapping("/option/change")
    ResultVo<?> change(@RequestBody SysOptionsEntity dto) {
        return adminOptionsService.change(dto);
    }

    @RequiresPermissions("option:list")
    @PostMapping("/option/list")
    ResultVo<?> queryList(@RequestBody SysOptionDto dto) {
        return adminOptionsService.queryList(dto);
    }


}
