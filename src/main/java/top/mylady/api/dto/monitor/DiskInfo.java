package top.mylady.api.dto.monitor;

import lombok.Data;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/6/21 17:10
 * @Description:
 */
@Data
public class DiskInfo {

    private String diskName;  // 盘符路径
    private String diskType;  // 盘符类型
    private String fileName;  // 文件系统
    private String total;     // 总大小
    private String free;      // 剩余大小
    private String used;      // 已经使用量
    private double usage;     // 资源的使用率

}
