package cn.nanchengyu.usercenter.exception;

import cn.nanchengyu.usercenter.common.BaseResponse;
import cn.nanchengyu.usercenter.common.ErrorCode;
import cn.nanchengyu.usercenter.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ClassName: GlobalException
 * Package: cn.nanchengyu.usercenter.exception
 * Description:
 * 全局异常处理器
 *
 * @Author 南城余
 * @Create 2024/3/10 18:12
 * @Version 1.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse businessExceptionHandler(BusinessException e){
        log.error("businessException:"+ e.getMessage(),e);
        return ResultUtils.error(e.getCode(),e.getMessage(), e.getDescription());
    }
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse runtimeExceptionHandler(BusinessException e){
        log.error("runtimeException:"+ e.getMessage(),e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR,e.getMessage(),"");
    }
}
