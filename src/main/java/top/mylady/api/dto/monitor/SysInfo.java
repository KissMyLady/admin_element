package top.mylady.api.dto.monitor;

import lombok.Data;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/6/22 20:52
 * @Description:
 */
@Data
public class SysInfo {

    private String computerName;  // 服务器名称
    private String computerIp;    // 服务器Ip
    private String userName;      // 启动用户名
    private String userDir;       // 项目路径
    private String osName;        // 操作系统
    private String osArch;        // 系统架构

}
