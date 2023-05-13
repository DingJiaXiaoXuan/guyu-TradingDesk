package com.guyu.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CateMain {
    //一级分类实体类
    private Integer main_id;        //编号
    private String  main_name;      //一级分类名称
    private Integer main_status;    //启用状态：1、启用; 0、未启用;
    private Date    create_time;    //创建时间
    private Date    update_time;    //更新时间

    private List<Category> categoryList;     //二级分类
}
