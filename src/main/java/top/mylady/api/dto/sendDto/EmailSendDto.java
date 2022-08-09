package top.mylady.api.dto.sendDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;


/**
 * @Author: KissMyLady
 * @Date: Created in 2022/5/18 11:11
 * @Description:
 */
@Data
public class EmailSendDto implements Serializable {

    //字符串不能为null,字符串trim() 后也不能等于“”
    @NotBlank(message = "请填写收件人地址")
    private String toAddress;

    @NotBlank(message = "请填写发送主题")
    private String subject;

    @NotBlank(message = "请填写发送内容")
    private String content;

    //系统调用填写伪数据即可
    @NotBlank(message = "应用key不能为空")
    private String appKey;

    //系统调用填写伪数据即可
    @NotBlank(message = "时间戳不能为空")
    private String timeStamp;

    //系统调用填写伪数据即可
    @NotBlank(message = "校验值不能为空")
    private String signature;


    private int sendMethodCode = 2;  //不参与校验. 发送方式, 2, 接口调用, 3: 内部调用
    private String userCode;         //用户名, 用户工号
    private String userName;         //用户名, 用户工号
    private String userNickName;     //姓名

    private String requestURL;  //url地址
    private String reqMethod;   //请求方法
    private String header;      //请求头
}
