package cn.nanchengyu.usercenter.common;

/**
 * ClassName: ResultUtils
 * Package: cn.nanchengyu.usercenter.common
 * Description:
 *
 * @Author 南城余
 * @Create 2024/3/10 14:40
 * @Version 1.0
 */
public class ResultUtils {
    public static <T> BaseResponse<T> success(T data){
        return new BaseResponse<>(0,data,"ok");

    }
    public static BaseResponse error(ErrorCode errorCode){
        return new BaseResponse<>(errorCode);
    }
    public static BaseResponse error(int code,String message,String description){
        return new BaseResponse<>(code,message,description);
    }

    public static BaseResponse error(ErrorCode errorCode,String message,String description){
        return new BaseResponse<>(errorCode.getCode(),message,description);
    }
    public static BaseResponse error(ErrorCode errorCode,String description){
        return new BaseResponse<>(errorCode.getCode(),errorCode.getMessage(),description);
    }
}
