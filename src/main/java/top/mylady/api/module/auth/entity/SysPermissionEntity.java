package top.mylady.api.module.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 后台权限表
 * </p>
 *
 * @author mylady
 * @since 2022-06-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_permission")
public class SysPermissionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自定id,主要供前端展示权限列表分类排序使用.
     */
    @TableId("id")
    private Integer id;

    /**
     * 归属菜单,前端判断并展示菜单使用,
     */
    @TableField("menu_code")
    private String menuCode;

    /**
     * 菜单的中文释义
     */
    @TableField("menu_name")
    private String menuName;

    /**
     * 权限的代码/通配符,对应代码中@RequiresPermissions 的value
     */
    @TableField("permission_code")
    private String permissionCode;

    /**
     * 本权限的中文释义
     */
    @TableField("permission_name")
    private String permissionName;

    /**
     * 是否本菜单必选权限, 1.必选 2非必选 通常是"列表"权限是必选
     */
    @TableField("required_permission")
    private String requiredPermission;


}
