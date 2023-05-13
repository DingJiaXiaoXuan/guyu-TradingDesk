package com.guyu.service.impl;


import com.guyu.entity.User;
import com.guyu.mapper.UserMapper;
import com.guyu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean login(String account, String password) {
        if (StringUtils.isEmpty(account)||StringUtils.isEmpty(password)){
            return false;
        }
        String pwd= userMapper.findPassword(account);
        if(pwd==null){
            return false;
        }
        else if(pwd.equals(password)){
            userMapper.oid_logintime(account,userMapper.findLogintime(account));//记录上一次登录时间
            userMapper.loginTime(account);//本次登录时间
            return true;
        }
        return false;
    }

    @Override
    public User UserFrom(String account){
        return userMapper.findUser(account);
    }

    @Override
    public List<User> UserPage(Integer start, Integer point) {
        return userMapper.findAll(start,point);
    }

    @Override
    public Integer Total() {
        return userMapper.findTotal();
    }

    @Override
    public String nameinfo(String account) {
        return userMapper.nameinfo(account);
    }

    @Override
    public boolean usersave(User user) {
        if(StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getAccount())
                || StringUtils.isEmpty(user.getPassword()) || StringUtils.isEmpty(user.getUserphone())
                || StringUtils.isEmpty(user.getEmail()) || StringUtils.isEmpty(user.getPower()))
        {
            return false;
        }
        /**
         * 如果该条信息已存在，就修改信息
         */
        if(!StringUtils.isEmpty(user.getUserid())){
            if (userMapper.updateUser(user)==1) {
                return true;
            }
        }
        String name = userMapper.findName(user.getUsername());
        if(name!=null){
            return false;
        }
        Integer id = userMapper.findID(user.getAccount());
        if(id==null){
            userMapper.insertUser(user);
            return true;
        }
        return false;
    }

    @Override
    public Integer delUser(String account) {
        return  userMapper.deluser(account);
    }

    @Override
    public Integer dels(List<Integer> ids) {
        return userMapper.dels(ids);
    }

    @Override
    public Integer updatauser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public String register(User user) {
        /**
         * 对注册信息进行非空判断
         */
        if(StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getAccount()) || StringUtils.isEmpty(user.getPassword()) || StringUtils.isEmpty(user.getUserphone())){
            return "当前用户信息不完整";
        }
        if(user.getAccount().length()<6 || user.getPassword().length()<6 || user.getUserphone().length()!=11){
            return "当前用户信息不符合要求";
        }
        String name = userMapper.findName(user.getUsername());
        if(name!=null){
            return "当前用户名已存在";
        }
        String pwd = userMapper.findPassword(user.getAccount());
        if(pwd==null) {
            userMapper.regUser(user);
            return "注册成功";
        }
        return "该用户已存在";
    }
}
