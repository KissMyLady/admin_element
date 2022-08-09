package top.mylady.api.dto.app.file;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/7/28 21:56
 * @Description:
 */
@Data
public class ChangeFileTypeDto {

    @NotEmpty
    private List<String> ids;

    @NotBlank
    private String typeId;

}
