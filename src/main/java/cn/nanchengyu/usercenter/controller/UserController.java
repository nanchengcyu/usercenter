package cn.nanchengyu.usercenter.controller;

import cn.nanchengyu.usercenter.constant.UserConstant;
import cn.nanchengyu.usercenter.model.domain.User;
import cn.nanchengyu.usercenter.model.domain.request.UserLoginRequest;
import cn.nanchengyu.usercenter.model.domain.request.UserRegisterRequest;
import cn.nanchengyu.usercenter.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: UserCOntroller
 * Package: cn.nanchengyu.usercenter.controller
 * Description:
 *
 * @Author 南城余
 * @Create 2024/3/9 16:10
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Long userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            return null;
        }
        String userPassword = userRegisterRequest.getUserPassword();
        String userAccount = userRegisterRequest.getUserAccount();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(userPassword, userAccount, checkPassword)) {
            return null;
        }
        return userService.userRegister(userAccount, userPassword, checkPassword);


    }

    @PostMapping("/login")
    public User userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            return null;
        }
        String userPassword = userLoginRequest.getUserPassword();
        String userAccount = userLoginRequest.getUserAccount();
        if (StringUtils.isAnyBlank(userPassword, userAccount)) {
            return null;
        }
        return userService.userLogin(userAccount, userPassword, request);


    }

    @GetMapping("/search")
    public List<User> searchUsers(String username) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.like("username", username);
        }
        List<User> users = userService.list(queryWrapper);

        return users.stream().map(user -> userService.getSafetyUser(user)).collect(Collectors.toList());
    }

    @DeleteMapping
    public boolean deleteUser(@RequestBody long id, HttpServletRequest request) {

        if (!isAdmin(request)) {
            return false;
        }
        if (id <= 0) {
            return false;
        }
        return userService.removeById(id);
    }

    private boolean isAdmin(HttpServletRequest request) {
        //仅管理员可查询
        Object userObj = request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        User user = (User) userObj;
        return user != null && user.getUserRole() == UserConstant.ADMIN_ROLE;
    }
}
