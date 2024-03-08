package cn.nanchengyu.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.nanchengyu.usercenter.model.domain.User;
import cn.nanchengyu.usercenter.service.UserService;
import cn.nanchengyu.usercenter.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author nanchengyu
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2024-03-08 21:58:54
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Autowired
    UserMapper userMapper;
    /**
     * 盐值混淆密码
     */
    private static final  String SALT ="ncy";
    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        //1. 非空
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return -1;
        }
        //账号不能超过4位
        if (userAccount.length() < 4) {
            return -1;
        }
        //密码不能小于8位
        if (userPassword.length() < 8) {
            return -1;
        }
        //todo 账号不能包含特殊字符
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";

        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(userAccount);

        if (matcher.find()){
            return -1;
        }
        //账号是否重复
        QueryWrapper<User> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("userAccount",userAccount);
        long count = userMapper.selectCount(queryWrapper);
        if (count >0){
            return -1;
        }
        //2. 加密

        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        // 3. 将用户账号和密码插入数据库
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        boolean result = this.save(user);
        if (!result){
            return -1;
        }

        return user.getId();
    }

    @Override
    public User doLogin(String userAccount, String userPassword) {
        //1. 非空
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }
        //账号不能超过4位
        if (userAccount.length() < 4) {
            return null;
        }
        //密码不能小于8位
        if (userPassword.length() < 8) {
            return null;
        }
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";

        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(userAccount);

        if (matcher.find()){
            return null;
        }

        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());

        //查询用户是否存在
        QueryWrapper<User> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("userAccount",userAccount);
        queryWrapper.eq("userPassword",encryptPassword);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null){
            return null;
        }

        return user;
    }
}




