package cn.nanchengyu.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: UserRegisterRequest
 * Package: cn.nanchengyu.usercenter.model.domain.request
 * Description:
 *
 * @Author 南城余
 * @Create 2024/3/9 16:16
 * @Version 1.0
 */
@Data
public class UserLoginRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userAccount;
    private String userPassword;




}
