package top.mylady.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/6/13 14:58
 * @Description: 通用查询
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommonQueryDto extends BaseQueryDto {

    //查询关键词
    private String key;

    //删除操作码
    private String option;

    private String id;


}
