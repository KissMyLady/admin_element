package top.mylady.api.dto.sendDto;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/5/18 16:35
 * @Description:
 */
@Data
public class DistributionKeyDto {

    @Max(value = 90, message = "过期时间不能大于90天")
    @Min(value = 1, message = "过期时间不能小于1天")
    private int key_overdue=30;      //过期时间, 默认30天

    @Max(value = 9999, message = "短信发送限制数量不能大于9999条")
    @Min(value = 1, message = "短信发送限制数量不能小于1条")
    private int key_sms_count=100;   //短信发送限制数量

    @Max(value = 9999, message = "邮箱发送限制数量不能大于9999条")
    @Min(value = 1, message = "邮箱发送限制数量不能小于1条")
    private int key_email_count=100; //邮箱发送限制数量


    private String userId;    //查询用户Id
    private String userName;   //查询用户名称

}
