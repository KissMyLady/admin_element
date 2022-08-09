package top.mylady.api.module.sys_manage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author mylady
 * @since 2022-06-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_options")
public class SysOptionsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "op_id", type = IdType.AUTO)
    private Integer opId;

    /**
     * 标题
     */
    @TableField("op_title")
    private String opTitle;

    @TableField("op_key")
    private String opKey;

    @TableField("op_value")
    private String opValue;

    @TableField("op_remake")
    private String opRemake;

    /**
     * 逻辑删除
     */
    @TableField("is_delete")
    private Boolean isDelete;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;


}
