package top.mylady.api.dto.app.file;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.mylady.api.dto.BaseQueryDto;

import java.util.List;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/7/28 15:51
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FileTypeDto extends BaseQueryDto {

    private String keyword;

    private List<String> ids;

    private String id;

    //删除操作码, 1:删除, 0:不删除
    private String option = "1";

}
