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
 * app-marry小程序 的日志表
 * </p>
 *
 * @author mylady
 * @since 2022-07-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("marry_app_logs")
public class MarryAppLogsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 日志类型
     */
    @TableField("log_type")
    private String logType;

    /**
     * 日志的级别
     */
    @TableField("log_level")
    private String logLevel;

    /**
     * 日志标题
     */
    @TableField("title")
    private String title;

    /**
     * 操作IP地址
     */
    @TableField("ip_address")
    private String ipAddress;

    /**
     * 请求头
     */
    @TableField("user_agent")
    private String userAgent;

    /**
     * 请求URI
     */
    @TableField("request_url")
    private String requestUrl;

    /**
     * 操作方式
     */
    @TableField("method")
    private String method;

    /**
     * 执行时间
     */
    @TableField("timeout")
    private Long timeout;

    /**
     * 操作提交的数据
     */
    @TableField("params")
    private String params;

    /**
     * 异常捕获
     */
    @TableField("exception")
    private String exception;

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
