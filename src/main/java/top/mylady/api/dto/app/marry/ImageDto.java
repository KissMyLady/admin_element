package top.mylady.api.dto.app.marry;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.mylady.api.dto.BaseQueryDto;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/7/16 21:01
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ImageDto extends BaseQueryDto {

    private String id;

    //删除操作码, 1:删除, 0:不删除
    private String option="1";

    //查询关键字
    private String keyword;

    private Integer pageSize=10;

}
