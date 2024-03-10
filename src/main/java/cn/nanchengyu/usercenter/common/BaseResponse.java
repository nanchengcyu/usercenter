package cn.nanchengyu.usercenter.common;

import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: BaseResponse
 * Package: cn.nanchengyu.usercenter.common
 * Description:
 *
 * @Author 南城余
 * @Create 2024/3/10 14:30
 * @Version 1.0
 */
@Data //自动生成参数的get set方法
public class BaseResponse<T> implements Serializable {
    private int code;
    private T data;
    private String message;
    private String description;
    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;

    }

    public BaseResponse(int code, T data, String message, String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = description;
    }

    public BaseResponse(int code, T data) {
        this(code, data, "","");
    }
    public BaseResponse(ErrorCode errorCode){
        this(errorCode.getCode(),null,errorCode.getMessage(),errorCode.getDescription());
    }


}
