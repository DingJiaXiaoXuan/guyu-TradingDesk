package com.guyu.controller;

import com.guyu.entity.User;
import com.guyu.service.UserService;
import com.guyu.utils.JsonResult;
import com.guyu.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController{

    @Autowired
    UserService userService;

    /**
     * 登录
     * @param user
     * @return  JsonResult<>
     */
    @RequestMapping("/login")
    @ResponseBody
    public JsonResult<User> login(@RequestBody User user){
        if (userService.login(user.getAccount(),user.getPassword())){
            user = userService.UserFrom(user.getAccount());
            user.setPassword(null);
            return  new JsonResult<>(1000,"OK",user);
        }
        return  new JsonResult<>(400,"NO");
    }
    /**
     * 注册
     * @param user
     * @return  状态说明
     */
    @RequestMapping("/register")
    public String register(@RequestBody User user){
        return userService.register(user);
    }

    /**
     * 分页查询
     * @param page
     * @return page
     */
    @RequestMapping("/PagingUser")
    public Page Page(@RequestBody Page page){
        page.setStart();
        page.setPoint();
        page.setCountSun(userService.Total());
        page.setPageSun();

        page.setData(userService.UserPage(page.getStart(), page.getPoint()));
        return page;
    }

    /**
     * 获取用户信息
     * @param user
     * @return
     */
    @RequestMapping("/user")
    public User userinfo(@RequestBody User user){
        user = userService.UserFrom(user.getAccount());
        user.setPassword("");
        user.setUserid(0);

        return user;
    }

    /**
     * 获取用户昵称
     * @param user
     * @return  用户昵称(username)
     */
    @RequestMapping("/username")
    public String username(@RequestBody User user){
        return userService.nameinfo(user.getAccount());
    }

    /**
     * 添加一条用户信息
     * @param user
     * @return
     */
    @RequestMapping("/usersave")
    public boolean userSave(@RequestBody User user){
        System.out.println(user.getImage());
        return userService.usersave(user);
    }

    /**
     * 删除用户信息
     * @param user
     */
    @RequestMapping("/deluser")
    public Integer deluser(@RequestBody User user){
        return userService.delUser(user.getAccount());
    }

    /**
     * 批量删除
     * @param ids
     * @return  状态码
     */
    @PostMapping("/userdels")
    public int dels(@RequestBody List<Integer> ids){
        if (!ids.isEmpty()){
            return userService.dels(ids);
        }else {
            return 0;
        }
    }

    @RequestMapping("/UpdateUser")
    public Integer UpdateUser(@RequestBody User user){
        System.out.println(user.getImage());
        return userService.updatauser(user);
    }
}