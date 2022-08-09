package top.mylady.api.config.exception;

import lombok.Data;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/6/7 9:31
 * @Description:
 */
public interface BaseMsg {

    /**
     * 获取消息的状态码
     */
    Integer getCode();

    /**
     * 获取消息提示信息
     */
    String getMessage();


}
