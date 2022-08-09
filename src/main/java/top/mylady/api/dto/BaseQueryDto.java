package top.mylady.api.dto;

import lombok.Data;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/6/6 17:51
 * @Description:
 */
@Data
public class BaseQueryDto {

    //分页的页码参数：从1开始
    private Integer page=1;

    //分页的页数据量参数，默认20
    private Integer pageSize=20;

    private Integer total=1;

}
