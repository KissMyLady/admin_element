package top.mylady.api.dto.sysDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/6/18 9:35
 * @Description: redis模拟数据, 进行增删改
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedisDto implements Serializable {

    private static final long serialVersionUID = 2892248514883451461L;

    private Long id;

    private String name;

}
