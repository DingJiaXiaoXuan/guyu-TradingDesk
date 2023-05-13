package com.guyu.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Market {
    //当前行情实体类
    private Integer market_id;              //行情编号
    private String market_name;             //产品名称
    private String origin;                  //地址
    private double price;                   //单价
    private String unit;                    //单位
    private Date create_time;               //时间
}
