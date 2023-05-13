package com.guyu.mapper;


import com.guyu.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface UserMapper {

    /**
     * 查询用户信息
     * @return  List<User>
     */
    List<User> findAll(@Param("start") Integer start,@Param("point") Integer point);

    /**
     * 基于账号查找用户信息
     * @return
     */
    User findUser(String account);

    /**
     * 登录
     * 基于账号查找密码
     * @param account
     * @return password
     */
    String findPassword(String account);

    /**
     * 基于账号查找id
     * @param account
     * @return
     */
    Integer findID(String account);

    /**
     * 查询用户名是否存在
     * @param username
     * @return  用户名
     */
    String findName(String username);

    /**
     * 添加一条用户信息
     * @param user
     * @return
     */
    void insertUser(User user);

    /**
     * 修改用户信息
     * @param user
     * @return  状态码
     */
    Integer updateUser(User user);

    /**
     * 注册
     * username,account,password，userphone
     * @param user
     */
    void regUser(User user);

    /**
     * 登录时间
     * @param account
     */
    void loginTime(String account);

    /**
     * 查询当前用户权限
     * @param account
     * @return power
     */
    int findPower(String account);

    /**
     * 查询用户信息总条数
     * @return 总条数
     */
    Integer findTotal();

    /**
     * 基于用户名查询用户昵称
     * @param account
     * @return
     */
    String nameinfo(String account);

    /**
     * 删除用户信息
     * @param account
     */
    Integer deluser(String account);

    /**
     * 批量删除用户信息
     * @param ids
     * @return
     */
    Integer dels(List<Integer> ids);

    /**
     * 上次登录时间
     * @param account
     * @param logintime
     */
    void oid_logintime(@Param("account") String account,@Param("logintime") Date logintime);

    /**
     * 查询登录时间
     * @param account
     * @return
     */
    Date findLogintime(String account);
}
