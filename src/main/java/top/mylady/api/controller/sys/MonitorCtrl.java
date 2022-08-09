package top.mylady.api.controller.sys;

import cn.hutool.system.oshi.CpuInfo;
import cn.hutool.system.oshi.OshiUtil;
import top.mylady.api.config.annotation.RequiresPermissions;
import top.mylady.api.config.ctrlConfig.ResultVo;
import top.mylady.api.dto.monitor.DiskInfo;
import top.mylady.api.dto.monitor.JvmInfo;
import top.mylady.api.dto.monitor.MemoryInfo;
import top.mylady.api.dto.monitor.SysInfo;
import top.mylady.api.util.model.SystemInfoUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/6/21 14:59
 * @Description: 系统监控
 */
@RestController
@RequestMapping("/monitorCtrl")
public class MonitorCtrl {

    //查询服务器信息
    @RequiresPermissions("sysMonitor:list")
    @PostMapping("/system")
    public ResultVo<?> getSystemInfo() {
        SystemInfoUtil util = new SystemInfoUtil();
        SysInfo sysInfo = util.getSysInfo();
        return ResultVo.success(sysInfo);
    }

    //查询CPU信息
    @RequiresPermissions("sysMonitor:list")
    @PostMapping("/cpu")
    public ResultVo<?> getSysCpu() {
        CpuInfo cpuInfo = OshiUtil.getCpuInfo();
        //logger.info("查询到的cpu: {}", cpuInfo);
        return ResultVo.success(cpuInfo);
    }

    //查询内存信息
    @RequiresPermissions("sysMonitor:list")
    @PostMapping("/mem")
    public ResultVo<?> getSysMem() {
        SystemInfoUtil util = new SystemInfoUtil();
        MemoryInfo memoryInfo = util.getMemoryInfo();
        return ResultVo.success(memoryInfo);
    }

    //查询JVM信息
    @RequiresPermissions("sysMonitor:list")
    @PostMapping("/jvm")
    public ResultVo<?> getSysJvm() {
        SystemInfoUtil util = new SystemInfoUtil();
        JvmInfo jvmInfo = util.getJvmInfo();
        return ResultVo.success(jvmInfo);
    }

    //查询磁盘信息
    @RequiresPermissions("sysMonitor:list")
    @PostMapping("/disk")
    public ResultVo<?> getSysDisk(){
        SystemInfoUtil util = new SystemInfoUtil();
        List<DiskInfo> diskInfo = util.getDiskInfo();
        return ResultVo.success(diskInfo);
    }

}
