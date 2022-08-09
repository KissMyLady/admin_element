package top.mylady.api.controller.sys;

import top.mylady.api.config.annotation.RequiresPermissions;
import top.mylady.api.config.ctrlConfig.ResultVo;
import top.mylady.api.dto.sysDto.SysLogDto;
import top.mylady.api.module.sys_manage.mapper.SysLogsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/6/15 9:56
 * @Description:
 */
@RestController
@RequestMapping("/sysCtrl")
public class SysCtrl {

    private static final Logger logger = LoggerFactory.getLogger(SysCtrl.class);

    @Resource
    private SysLogsMapper sysLogsMapper;


    @RequiresPermissions("sys:deleteLogs")
    @PostMapping("/deleteLogs")
    public ResultVo<?> deleteLogs(@RequestBody SysLogDto dto){
        int days = 90;  //删除多少天前的日志
        if(dto.getDays() != null && !dto.getDays().equals("")){
            days = Integer.parseInt(dto.getDays());
        }
        boolean b = sysLogsMapper.deleteLogs(days);
        return ResultVo.success("移除成功");
    }


}
