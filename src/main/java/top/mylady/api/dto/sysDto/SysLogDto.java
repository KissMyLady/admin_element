package top.mylady.api.dto.sysDto;
import lombok.EqualsAndHashCode;
import top.mylady.api.dto.BaseQueryDto;
import lombok.Data;

import java.util.List;


/**
 * @Author: KissMyLady
 * @Date: Created in 2022/6/15 9:59
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysLogDto extends BaseQueryDto {

    private String days;

    private String key; //关键字查询
    private String id;  //根据id查询

    //删除对象
    private List<String> ids;

    //日志查询级别
    private String logLv;

}
