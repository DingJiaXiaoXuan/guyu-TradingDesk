package com.guyu.entity;


import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
public class Product {
    //商品类
    private Integer pro_id;      //商品id
    private String  pro_name;   //商品名称
    private Integer cate_id;    //商品种类编号
    private String  origin;     //产地
    private Integer amount;     //商品销量
    private Integer price;      //商品单价
    private String  image;      //商品照片
    private String  title;      //商品标题
    private String  detail;     //商品简介
    private Integer pro_status;     //上架状态，1，上架，0，下架，2，审核中，3，审核失败
    private Integer user_id;     //用户id
    private Integer store_id;    //店铺id
    private Date    create_time; //创建时间
    private Date    update_time; //更新时间

    private Store store;        //店铺信息
    private User  user;         //用户信息
    private Category category;  //种类信息
}
