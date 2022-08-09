package top.mylady.api.config.ctrlConfig;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/6/6 15:50
 * @Description:
 */
@Data
public class ResultVo<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String DEF_SUCCESS_MSG = "成功";
    public static final String DEF_ERROR_MSG = "操作失败！";

    @JsonProperty("success")
    private boolean success;    //成功状态

    @JsonProperty("msg")
    private String msg;         //消息

    @JsonProperty("code")
    private Integer code;       //状态码

    @JsonProperty("timestamp")
    private Long timestamp;     //时间戳

    @JsonProperty("data")
    private T data;             //数据对象

    //@JsonProperty("info")
    //private T info;             //数据对象

    //计数
    @JsonProperty("length")
    private Integer length;

    public T getData() {
        return data;
    }

    public ResultVo<T> setData(T data) {
        this.data = data;
        return this;
    }

    //public ResultVo<T> setInfo(T info) {
    //    this.info = info;
    //    return this;
    //}

    public String toJsonStr(){
        return JSONObject.toJSONString(this);
    }

    public ResultVo() {
        this.success = true;
        this.msg = DEF_SUCCESS_MSG;
        this.code = HttpStatus.OK.value();
        this.timestamp = System.currentTimeMillis();
    }


    // ===================================== 成功 =====================================

    //成功1
    @JsonIgnore
    public static ResultVo<Object> success() {
        return new ResultVo<>();
    }


    //成功2
    @JsonIgnore
    public static ResultVo<Object> success(String msg) {
        ResultVo<Object> ret = new ResultVo<>();
        ret.setMsg(msg);
        return ret;
    }

    //成功3
    @JsonIgnore
    public static <T> ResultVo<T> success(T data) {
        ResultVo<T> ret = new ResultVo<>();
        ret.setData(data);
        //ret.setInfo(data);
        return ret;
    }


    //成功3.5
    @JsonIgnore
    public static <T> ResultVo<T> success(T data, String msg) {
        ResultVo<T> ret = new ResultVo<>();
        ret.setData(data);
        ret.setMsg(msg);
        return ret;
    }

    @JsonIgnore
    public static <T> ResultVo<T> success(T data, Integer length) {
        ResultVo<T> ret = new ResultVo<>();
        ret.setData(data);
        ret.setLength(length);
        return ret;
    }

    //成功5
    @JsonIgnore
    public static <T> ResultVo<T> success(Integer code, String msg, T data) {
        ResultVo<T> ret = new ResultVo<>();
        ret.setCode(code);
        ret.setMsg(msg);
        ret.setData(data);
        return ret;
    }



    // ===================================== 失败 =====================================

    //错误1
    @JsonIgnore
    public static ResultVo<Object> error() {
        return ResultVo.error(
                HttpStatus.INTERNAL_SERVER_ERROR.value()
                , DEF_ERROR_MSG, null);
    }

    //错误2
    @JsonIgnore
    public static ResultVo<Object> error(String msg) {
        return ResultVo.error(
                HttpStatus.INTERNAL_SERVER_ERROR.value()
                , msg, null);
    }

    //错误3
    @JsonIgnore
    public static ResultVo<Object> error(Integer code, String msg) {
        return ResultVo.error(code, msg, null);
    }


    //错误4
    @JsonIgnore
    public static <T> ResultVo<T> error(Integer code, String msg, T data) {
        ResultVo<T> ret = new ResultVo<>();
        ret.setMsg(msg);
        ret.setCode(code);
        ret.setData(data);
        ret.setSuccess(false);
        return ret;
    }

}
