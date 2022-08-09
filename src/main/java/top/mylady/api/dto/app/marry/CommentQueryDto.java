package top.mylady.api.dto.app.marry;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.mylady.api.dto.BaseQueryDto;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/7/16 14:20
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CommentQueryDto extends BaseQueryDto {

    private String id;

    //查询关键字
    private String key;

    private Integer pageSize=5;

}
