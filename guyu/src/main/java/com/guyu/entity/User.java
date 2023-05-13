package com.guyu.entity;

import lombok.Data;


import java.util.Date;
import java.util.Objects;


@Data
public class User {
   //用户类
   private Integer userid;          //id
   private String username;         //昵称
   private String account;          //账号
   private String password;         //密码
   private String image;            //头像
   private String userphone;        //电话
   private Date logintime;        //登录时间
   private Date old_logintime;      //上次登录时间
   private String email;            //邮箱
   private int power;               //权限


}
