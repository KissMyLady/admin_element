package top.mylady.api.dto.sendDto;

import top.mylady.api.dto.BaseQueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/6/6 17:51
 * @Description:
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class MsgMediumCushionDto extends BaseQueryDto {

    //轮询表查询字段
    private String keyword;


}
