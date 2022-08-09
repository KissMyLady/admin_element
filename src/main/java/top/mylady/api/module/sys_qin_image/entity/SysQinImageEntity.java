package top.mylady.api.module.sys_qin_image.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.sql.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 七牛文件云存储
 * </p>
 *
 * @author mylady
 * @since 2022-07-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_qin_image")
public class SysQinImageEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    //本地服务器云存储url
    @TableField("view_url")
    private String viewUrl;

    //拼接全url
    @TableField("file_url")
    private String fileUrl;

    //文件名
    @TableField("file_key")
    private String fileKey;

    //云存储名标识
    @TableField("file_hash")
    private String fileHash;

    /**
     * 文件大小
     */
    @TableField("file_size")
    private String fileSize;

    /**
     * 文件类型
     */
    @TableField("file_type")
    private String fileType;

    /**
     * 存储类型
     */
    @TableField("storage_type")
    private String storageType;

    /**
     * 描述
     */
    @TableField("desc_txt")
    private String descTxt;

    /**
     * 展示顺序
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
