package com.guyu.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Category {
    //二级分类实体类
    private Integer cate_id;        //编号
    private Integer main_id;        //主类别编号
    private String  cate_name;      //种类名称
    private Integer cate_status;    //状态:1,启用;0,未启用;
    private Date    create_time;    //创建时间
    private Date    update_time;    //更新时间

    private CateMain cateMain;      //主类别
}
