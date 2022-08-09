package top.mylady.api.dto.monitor;

import lombok.Data;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/6/22 16:52
 * @Description:
 */
@Data
public class JvmInfo {

    private String jvmName;    // 虚拟机名称
    private String total;      // 当前JVM占用的内存总数(M)
    private String max;        // JVM最大可用内存总数(M)
    private String free;       // JVM空闲内存(M)
    private String used;       // 已使用
    private double usage;      // 使用率
    private String version;    // JDK版本
    private String home;       // JDK路径
    private String vendor;     // Java的运行环境供应商
    private String vendorUrl;  // Java的运行环境供应商 URL
    private String startTime;  // JDK启动时间
    private String runTime;    // JDK运行时间


}
