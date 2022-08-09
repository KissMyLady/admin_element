package top.mylady.api.config.exception;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/6/7 15:18
 * @Description:
 */
public enum ExceptionConstant implements BaseMsg{

    Exception_Vo(401, "实体类为空"),
    not_jtw(401, "没有token"),
    not_effect(401, "无效token"),
    EXCEPTION_No_Data(401, "数据不存在"),
    EXCEPTION_Params_Error(401, "参数类型错误")
    ;

    private final int code;
    private final String message;

    ExceptionConstant(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
