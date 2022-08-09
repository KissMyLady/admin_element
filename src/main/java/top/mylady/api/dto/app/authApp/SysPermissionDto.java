package top.mylady.api.dto.app.authApp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.mylady.api.dto.BaseQueryDto;

import java.util.List;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/7/29 8:08
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysPermissionDto extends BaseQueryDto {

    private String keyword;

    private List<String> ids;

    private String id;

    //删除操作码, 1:删除, 0:不删除
    private String option="1";

    //是否本菜单必选权限, 1.必选 2非必选 通常是"列表"权限是必选
    private String requiredPermission = "1";

}
