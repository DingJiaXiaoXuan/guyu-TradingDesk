package com.guyu.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Store {
    //店铺类
    private Integer store_id;       //店铺id
    private String  store_name;     //店铺名称
    private String  store_title;    //店铺简介
    private String  store_site;     //店铺地址
    private String  store_imge;     //店铺照片
    private Integer user_id;        //用户id
    private Integer pro_num;        //商品数量
    private Integer store_status;   //状态
    private Date    create_time;    //创建时间
    private Date    update_time;    //更新时间

    private User    user;           //用户信息
}
