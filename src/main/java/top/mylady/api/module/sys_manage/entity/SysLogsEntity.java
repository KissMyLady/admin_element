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
 * <p>
 * 日志表
 * </p>
 *
 * @author mylady
 * @since 2022-06-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_logs")
public class SysLogsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //唯一主键
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //日志类型
    @TableField("log_type")
    private String logType;

    //日志级别
    @TableField("log_level")
    private String logLevel;

    //日志标题
    @TableField("log_title")
    private String logTitle;

    //操作IP
    @TableField("req_ip")
    private String reqIp;

    //IP地址
    @TableField("req_address")
    private String reqAddress;

    //请求头
    @TableField("req_agent")
    private String reqAgent;

    //浏览器
    @TableField("req_browser")
    private String reqBrowser;

    //操作系统
    @TableField("req_system")
    private String reqSystem;

    //请求URI
    @TableField("req_url")
    private String reqUrl;

    //操作方式
    @TableField("req_method")
    private String reqMethod;

    //操作提交的数据
    @TableField("req_params")
    private String reqParams;

    //执行时间
    @TableField("time_out")
    private Long timeOut;

    //异常信息
    @TableField("exception")
    private String exception;

    //创建人
    @TableField("create_by")
    private String createBy;

    //修改人
    @TableField("update_by")
    private String updateBy;


    @TableField("is_delete")
    private Boolean isDelete;     //逻辑删除
    @TableField("create_time")

    private Date createTime;      //创建时间
    @TableField("update_time")

    private Date updateTime;      //更新时间


}
