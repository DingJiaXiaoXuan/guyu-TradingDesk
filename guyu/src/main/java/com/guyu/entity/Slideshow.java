package com.guyu.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Slideshow {
    //首页轮播图
    private Integer slide_id;           //轮播图id
    private String  slide_imge;         //轮播图URL
    private Integer slide_status;       //使用状态编号(1~4) ,0不使用
    private Date    create_time;        //创建时间
    private Date    update_time;        //更新时间
}
