package com.guyu.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Cart {
    //购物车类
    private Integer cart_id;         //购物车id
    private Integer user_id;        //用户id
    private Integer pro_id;         //商品id
    private Integer pro_num;        //商品数量
    private Integer size_id;        //规格id
    private double pro_sum;        //总金额
    private Date    create_time;    //创建时间
    private Date    update_time;    //更新时间

    private Product product;        //商品信息
    private Size    size;           //规格信息
}
