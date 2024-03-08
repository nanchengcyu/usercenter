package cn.nanchengyu.usercenter.service;

import cn.nanchengyu.usercenter.mapper.UserMapper;
import cn.nanchengyu.usercenter.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;


/**
 * ClassName: UserServiceTest
 * Package: cn.nanchengyu.usercenter.service
 * Description:
 *
 * @Author 南城余
 * @Create 2024/3/8 22:03
 * @Version 1.0
 */
@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    void AddUser() {

        User user = new User();
        user.setId(0L);
        user.setUserState(0);
        user.setEmail("ncy02@qq.com");
        user.setGender(0);
        user.setUserAccount("img");
        user.setUsername("南城余");
        user.setCreateTime(new Date());
        user.setCreateTime(new Date());
        user.setUserAccount("ncy02");
        user.setUserPassword("12345678");
        user.setIsDelete(0);
        user.setUserState(0);
        boolean result = userService.save(user);
        System.out.println(user.getId());
        Assertions.assertTrue(result);


    }

    @Test
    void userRegister() {
        String userAccount= "nanchengyu/@";
        String userPassword = "12345678";
        String checkPassword = "12345678";
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1,result);

//        String userAccount= "nanchengyu";
//        String userPassword = "12345678";
//        String checkPassword = "12345678";
//        long result = userService.userRegister(userAccount, userPassword, checkPassword);
//        Assertions.assertEquals(-1,result);

    }
}