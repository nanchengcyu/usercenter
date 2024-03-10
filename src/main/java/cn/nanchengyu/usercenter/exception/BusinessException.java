package cn.nanchengyu.usercenter.exception;

import cn.nanchengyu.usercenter.common.ErrorCode;
import lombok.Getter;

/**
 * ClassName: BusinessException
 * Package: cn.nanchengyu.usercenter.exception
 * Description:
 *
 * @Author 南城余
 * @Create 2024/3/10 15:35
 * @Version 1.0
 */
@Getter
public class BusinessException extends  RuntimeException {

    private final int code;
    private final String description;

    public BusinessException(String message,int code, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }
    public BusinessException(ErrorCode errorCode,String description) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = description;
    }
    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }
}
