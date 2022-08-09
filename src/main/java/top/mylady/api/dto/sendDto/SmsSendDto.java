package top.mylady.api.dto.sendDto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/5/18 11:12
 * @Description:
 */
@Data
public class SmsSendDto implements Serializable {

    @NotBlank(message="发送内容不能为空")
    private String content;

    @NotBlank(message="发送人不能为空")
    private String to;

    //系统级发送时可以简单忽略
    @NotBlank(message = "时间戳不能为空")
    private String timeStamp;

    //系统级发送时可以简单忽略
    @NotBlank(message = "应用key不能为空")
    private String appKey;

    //系统级发送时可以简单忽略
    @NotBlank(message = "校验值不能为空")
    private String signature;

    private int sendMethodCode = 2;  //接口, 还是内部调用, 发送方式
    private String userCode;         //用户名, 用户工号
    private String userName;         //用户名, 用户工号
    private String userNickName;     //姓名


    private String requestURL;  //url地址
    private String reqMethod;   //请求方法
    private String header;     //请求头


}
