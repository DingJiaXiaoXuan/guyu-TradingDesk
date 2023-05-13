package com.guyu.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Collect {
    //商品收藏实体类
    private Integer collect_id;         //收藏编号
    private Integer pro_id;             //商品编号
    private Integer user_id;            //用户编号
    private Date    create_time;        //创建时间

    private Product product;            //商品信息
    private Store store;                //店铺信息
}
