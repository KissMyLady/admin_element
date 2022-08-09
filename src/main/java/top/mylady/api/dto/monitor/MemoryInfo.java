package top.mylady.api.dto.monitor;

import lombok.Data;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/6/22 16:54
 * @Description:
 */
@Data
public class MemoryInfo {

    private String total;  // 内存总量
    private String used;   // 已用内存
    private String free;   // 剩余内存
    private double usage;  // 使用率

}
