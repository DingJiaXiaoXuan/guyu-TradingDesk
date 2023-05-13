package com.guyu.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Notice {
    //公告实体类
    private Integer notice_id;          //公告编号
    private String  notice_title;       //公告标题
    private String  user_name;          //发布者名称
    private String  content;            //公告内容
    private Integer notice_status;      //公告状态；1、发布；0,不发布
    private Date    create_time;        //创建时间
    private Date    update_time;        //修改时间
    private Date    publish_time;       //发布时间
}
