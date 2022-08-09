package top.mylady.api.dto.sendDto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/5/27 10:41
 * @Description:
 */
@Data
public class MQSendDto implements Serializable {

    private String name;
    private String note;

}
