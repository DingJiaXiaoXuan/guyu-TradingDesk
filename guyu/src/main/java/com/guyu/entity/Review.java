package com.guyu.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Review {
    //评论实体类
    private Integer review_id;          //评论编号
    private Integer pro_id;             //商品编号
    private Integer store_id;           //店铺编号
    private String  content;            //评论内容
    private Integer user_id;            //用户id
    private Integer review_status;      //状态:0.审核中;1.审核通过;2.审核失败
    private Date    create_time;        //创建时间
    private Date    update_time;        //修改时间

    private User    user;               //用户信息
    private Product product;            //商品信息
    private Store store;                //店铺信息
}
