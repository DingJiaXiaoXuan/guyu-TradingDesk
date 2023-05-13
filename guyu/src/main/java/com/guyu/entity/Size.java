package com.guyu.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Size {
    //商品规格
    private Integer size_id;        //规格id
    private Integer pro_id;         //商品id
    private String  size_title;     //规格描述
    private double  pro_price;      //单价
    private String unit;           //单位
    private Integer min_num;        //最低数量
    private Integer max_num;        //库存
    private Integer size_status;    //状态:0,使用;1,未使用
    private Date    create_time;    //创建时间
    private Date    update_time;    //更新时间
}
