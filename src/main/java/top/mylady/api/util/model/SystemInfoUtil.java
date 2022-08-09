package top.mylady.api.util.model;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.BetweenFormatter;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.system.HostInfo;
import cn.hutool.system.JavaInfo;
import cn.hutool.system.JavaRuntimeInfo;
import cn.hutool.system.OsInfo;
import cn.hutool.system.SystemUtil;
import cn.hutool.system.UserInfo;

import cn.hutool.system.oshi.CpuInfo;
import cn.hutool.system.oshi.OshiUtil;
import com.google.common.collect.Lists;
import top.mylady.api.dto.monitor.DiskInfo;
import top.mylady.api.dto.monitor.MemoryInfo;
import top.mylady.api.dto.monitor.SysInfo;
import top.mylady.api.dto.monitor.JvmInfo;

import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;

import java.lang.management.RuntimeMXBean;
import java.util.List;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/6/22 21:04
 * @Description: 系统信息工具类
 */
public class SystemInfoUtil {

    private static final long KB = 1024;
    private static final long MB = KB * 1024;
    private static final long GB = MB * 1024;

    /**
     * 获得系统信息
     */
    public SysInfo getSysInfo() {
        OsInfo osInfo = SystemUtil.getOsInfo();
        UserInfo userInfo = SystemUtil.getUserInfo();
        HostInfo hostInfo = SystemUtil.getHostInfo();

        SysInfo sysInfo = new SysInfo();

        sysInfo.setComputerName(hostInfo.getName());
        sysInfo.setComputerIp(hostInfo.getAddress());
        sysInfo.setUserName(userInfo.getName());
        sysInfo.setUserDir(userInfo.getCurrentDir());
        sysInfo.setOsArch(osInfo.getArch());
        sysInfo.setOsName(osInfo.getName());
        return sysInfo;
    }


    /**
     * 获取CPU信息
     */
    public CpuInfo getCpu(){
        return OshiUtil.getCpuInfo();
    }

    /**
     * 获取磁盘信息
     */
    public List<DiskInfo> getDiskInfo(){
        List<DiskInfo> diskInfoList = Lists.newArrayList();
        SystemInfo si = new SystemInfo();

        FileSystem fileSystem = si.getOperatingSystem().getFileSystem();
        OSFileStore[] fileStores = fileSystem.getFileStores();
        for (OSFileStore fs : fileStores) {
            long free = fs.getUsableSpace();
            long total = fs.getTotalSpace();
            long used = total - free;

            DiskInfo diskInfo = new DiskInfo();
            diskInfo.setDiskName(fs.getMount());
            diskInfo.setDiskType(fs.getType());
            diskInfo.setFileName(fs.getName());
            diskInfo.setTotal(this.sizeToString(total));
            diskInfo.setFree(this.sizeToString(free));
            diskInfo.setUsed(this.sizeToString(used));
            diskInfo.setUsage(NumberUtil.mul(NumberUtil.div(used, total, 4), 100));
            diskInfoList.add(diskInfo);
        }
        return diskInfoList;
    }

    /**
     * 获取内存信息
     */
    public MemoryInfo getMemoryInfo(){
        MemoryInfo memoryInfo = new MemoryInfo();
        GlobalMemory memory = OshiUtil.getMemory();
        if(memory != null){
            String total = Convert.toStr(memory.getTotal());
            String used = Convert.toStr(memory.getTotal() - memory.getAvailable());
            memoryInfo.setTotal(this.sizeToString(memory.getTotal()));
            memoryInfo.setUsed(this.sizeToString(memory.getTotal() - memory.getAvailable()));
            memoryInfo.setFree(this.sizeToString(memory.getAvailable()));
            memoryInfo.setUsage(NumberUtil.mul(NumberUtil.div(used, total, 4), 100).doubleValue());
        }
        return memoryInfo;
    }


    public JvmInfo getJvmInfo(){
        JavaInfo javaInfo = SystemUtil.getJavaInfo();
        JavaRuntimeInfo javaRuntimeInfo = SystemUtil.getJavaRuntimeInfo();
        RuntimeMXBean runtimeMxBean = SystemUtil.getRuntimeMXBean();
        long total = Runtime.getRuntime().totalMemory();
        long max = Runtime.getRuntime().maxMemory();
        long free = Runtime.getRuntime().freeMemory();
        long used = total - free;

        JvmInfo jvmInfo = new JvmInfo();

        // Jvm 信息
        jvmInfo.setTotal(this.sizeToString(total));
        jvmInfo.setMax(this.sizeToString(max));
        jvmInfo.setFree(this.sizeToString(free));
        jvmInfo.setUsed(this.sizeToString(used));
        
        jvmInfo.setUsage(NumberUtil.mul(
                NumberUtil.div(Convert.toStr(used),
                Convert.toStr(total), 4), 100).doubleValue());


        jvmInfo.setJvmName(runtimeMxBean.getVmName());  // 虚拟机名称
        jvmInfo.setVendor(javaInfo.getVendor());        // Java 供应商
        jvmInfo.setVendorUrl(javaInfo.getVendorURL());  // 链接
        jvmInfo.setHome(javaRuntimeInfo.getHomeDir());  // Java 安装目录
        jvmInfo.setVersion(javaInfo.getVersion());      // Java 版本
        long startTime = runtimeMxBean.getStartTime();  // JDK 启动时间戳

        // JDK 运行时间
        String runTimed = DateUtil.formatBetween(
                DateUtil.date(startTime), DateUtil.date(), BetweenFormatter.Level.SECOND);
        jvmInfo.setRunTime(runTimed);

        // JDK 启动时间
        jvmInfo.setStartTime(DateUtil.formatDateTime(DateUtil.date(startTime)));
        return jvmInfo;
    }

    public String sizeToString(long size) {
        float fileSize = convertFileSize(size);
        if (size >= GB) {
            return String.format("%.1f GB" , fileSize);
        } else if (size >= MB) {
            return String.format(fileSize > 100 ? "%.0f MB" : "%.1f MB" , fileSize);
        } else if (size >= KB) {
            float f = (float) size / KB;
            return String.format(fileSize > 100 ? "%.0f KB" : "%.1f KB" , fileSize);
        } else {
            return String.format("%d B" , size);
        }
    }

    public float convertFileSize(long size) {
        if (size >= GB) {
            return (float) size / GB;
        } else if (size >= MB) {
            return (float) size / MB;
        } else if (size >= KB) {
            return (float) size / KB;
        } else {
            return size;
        }
    }
}
