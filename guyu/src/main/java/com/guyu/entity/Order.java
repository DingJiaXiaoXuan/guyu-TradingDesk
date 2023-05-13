package com.guyu.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    //订单类
    private Integer ord_id;     //订单编号
    private Integer pro_id;     //商品id
    private Integer pro_num;    //商品数量
    private Integer size_id;    //规格id
    private Integer store_id;   //店铺id
    private Integer money;      //金额
    private Integer user_id;    //付款人
    private String  consignee;  //收货人
    private String  phone;      //收货人电话
    private String  address;    //收货地址
    private Integer ord_status; //状态:0.未付款；1.待发货；2.待确认收货；3：待评价，4.退款售后;5.交易成功，6.交易关闭，7.退款中，8退款成功，9.退款失败，10，退货，11，退货成功
    private String  comment;        //注备
    private Date    create_time;    //创建时间
    private Date    update_time;    //更新时间

    private Product product;    //  商品信息
    private User    user;       //用户信息
    private Size    size;       //规格信息
    private Store   store;      //店铺信息
}
