package top.mylady.api.module.marry.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * app-marry小程序 的操作码表
 * </p>
 *
 * @author mylady
 * @since 2022-07-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("marry_app_options")
public class MarryAppOptionsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "option_id", type = IdType.AUTO)
    private Integer optionId;

    /**
     * 标题
     */
    @TableField("option_title")
    private String optionTitle;

    /**
     * key
     */
    @TableField("option_name")
    private String optionName;

    /**
     * value
     */
    @TableField("option_value")
    private String optionValue;

    /**
     * 备注
     */
    @TableField("remake")
    private String remake;

    /**
     * 逻辑删除
     */
    @TableField("is_delete")
    private Boolean isDelete;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;


}
