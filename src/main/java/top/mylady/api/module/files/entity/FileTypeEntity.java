package top.mylady.api.module.files.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 文件模块类型
 * </p>
 *
 * @author mylady
 * @since 2022-07-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("file_type")
public class FileTypeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    @TableField("tp_name")
    private String tpName;

    /**
     * 跳转路径
     */
    @TableField("tp_url")
    private String tpUrl;

    /**
     * 封面图
     */
    @TableField("tp_img")
    private String tpImg;

    /**
     * 描述
     */
    @TableField("tp_desc")
    private String tpDesc;

    /**
     * 排序
     */
    @TableField("orders")
    private Integer orders;

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
