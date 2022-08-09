package top.mylady.api.dto.app.marry;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/7/16 18:09
 * @Description: 评论留言
 */
@Data
public class commentDto {

    private String wx_token;  //tx的校验码

    @NotBlank
    private String comment;       //留言内容

    private String userNickName;  // 用户昵称
    private String userAvatar;    // 用户头像
    private boolean userGender;   // 用户性别
    private String wxCode;        // 用户微信号

}
