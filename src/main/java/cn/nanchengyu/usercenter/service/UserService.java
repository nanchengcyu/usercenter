package cn.nanchengyu.usercenter.service;

import cn.nanchengyu.usercenter.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author nanchengyu
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2024-03-08 21:58:54
*/
public interface UserService extends IService<User> {
    /**
     * 用户注册
     * @param userAccount 用户账号
     * @param userPassword 用户密码
     * @param checkPassword 校验密码
     * @return 新用户ID
     */
    long userRegister(String userAccount,String userPassword,String checkPassword);

    /**
     *
     * @param userAccount 用户账号
     * @param userPassword 用户密码
     * @return 脱敏后得用户信息
     */
    User doLogin(String userAccount,String userPassword);

}
