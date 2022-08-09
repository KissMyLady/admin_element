package top.mylady.api.module.sys_manage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author mylady
 * @since 2022-06-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("domain_pool")
public class DomainPoolEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * url
     */
    @TableField("url")
    private String url;

    /**
     * 时间
     */
    @TableField("times")
    private String times;

    /**
     * 详情备注
     */
    @TableField("details")
    private String details;

    /**
     * 是否删除
     */
    @TableField("is_delete")
    private Boolean isDelete;


}
