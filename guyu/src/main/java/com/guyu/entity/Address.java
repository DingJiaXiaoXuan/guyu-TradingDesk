package com.guyu.entity;

import lombok.Data;

@Data
public class Address {
    //收货地址实体类
    private Integer address_id;         //收货地址编号
    private Integer user_id;            //用户编号
    private String consignee;           //收货人
    private String phone;               //联系电话
    private String  address;            //收货地址
    private int     address_status;     //状态
}
