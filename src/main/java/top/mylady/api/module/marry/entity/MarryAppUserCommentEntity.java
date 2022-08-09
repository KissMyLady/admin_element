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
 * app-marry小程序 的用户留言表
 * </p>
 *
 * @author mylady
 * @since 2022-07-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("marry_app_user_comment")
public class MarryAppUserCommentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户留言内容
     */
    @TableField("user_comment")
    private String userComment;

    /**
     * 用户昵称
     */
    @TableField("user_nick_name")
    private String userNickName;

    /**
     * 用户头像
     */
    @TableField("user_avatar")
    private String userAvatar;

    /**
     * 用户性别
     */
    @TableField("user_gender")
    private Boolean userGender;

    /**
     * 用户微信号
     */
    @TableField("wx_code")
    private String wxCode;

    /**
     * 留言时的ip地址
     */
    @TableField("ip_num")
    private String ipNum;

    /**
     * 地址
     */
    @TableField("ip_address")
    private String ipAddress;

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
