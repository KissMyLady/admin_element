package top.mylady.api.dto.app.adminApp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.mylady.api.dto.BaseQueryDto;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class SysOptionDto extends BaseQueryDto {

    //关键词查询
    private String key;

    private List<String> ids;

    private String id;

    private String option="1"; //1: 删除, 0, 启动

}
