package com.guyu.service;


import com.guyu.entity.User;
import com.guyu.mapper.UserMapper;
import com.guyu.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService  {
    /**
     * 登录查询
     * @param account
     * @param password
     * @return True/false
     */
    public boolean login(String account,String password);

    /**
     * 注册
     * @param user
     * @return  state 状态码
     */
    public String register(User user);

    /**
     * 返回用户信息
     * @param account
     * @return user
     */
    public User UserFrom(String account);

    /**
     * 分页查询
     * @param start
     * @param point
     * @return List<User>
     */
    public List<User> UserPage (Integer start,Integer point);

    /**
     * 用户信息总条数
     * @return  Integer
     */
    public Integer Total();

    /**
     * 查询用户昵称
     * @param account
     * @return
     */
    public String nameinfo(String account);

    /**
     * 保存用户信息
     * @param user
     * @return  true/false
     */
    public boolean usersave(User user);

    /**
     * 删除用户信息
     * @param account
     */
    public Integer delUser(String account);

    /**
     * 批量删除用户信息
     * @param ids
     * @return
     */
    public Integer dels(List<Integer> ids);

    /**
     * 修改账号信息
     * @param user
     * @return  状态码：1，成功；0，失败
     */
    public Integer updatauser(User user);
}
