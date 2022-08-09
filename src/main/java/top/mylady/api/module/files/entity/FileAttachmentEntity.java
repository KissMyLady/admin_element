package top.mylady.api.module.files.entity;

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
 * 系统附件表
 * </p>
 *
 * @author mylady
 * @since 2022-07-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("file_attachment")
public class FileAttachmentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 创建用户, 关联到用户
     */
    @TableField("ref_user_id")
    private Integer refUserId;

    /**
     * 关联到文件模块类型
     */
    @TableField("ref_type_id")
    private Integer refTypeId;

    /**
     * 全url路径
     */
    @TableField("res_url")
    private String resUrl;

    /**
     * 文件类型,后缀名
     */
    @TableField("file_ext")
    private String fileExt;

    /**
     *文件类型
     */
    @TableField("file_type")
    private String fileType;

    /**
     * 文件名称
     */
    @TableField("file_name")
    private String fileName;

    /**
     * 文件大小
     */
    @TableField("file_size")
    private String fileSize;

    /**
     * 文件本地存储, 绝对路径
     */
    @TableField("abs_path")
    private String absPath;

    /**
     * 文件hash计算值
     */
    @TableField("str_hash")
    private String strHash;

    /**
     * 文件云存储名称
     */
    @TableField("cloud_key")
    private String cloudKey;

    /**
     * 文件云存储路径
     */
    @TableField("cloud_url")
    private String cloudUrl;

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
