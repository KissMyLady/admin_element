package top.mylady.api.dto.login;

import lombok.Data;
import javax.validation.constraints.NotBlank;


/**
 * @Author: KissMyLady
 * @Date: Created in 2022/6/8 16:19
 * @Description:
 */
@Data
public class OptionDto {

    @NotBlank
    private String token;

}
