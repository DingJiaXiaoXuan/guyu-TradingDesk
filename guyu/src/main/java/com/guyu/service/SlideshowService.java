package com.guyu.service;

import com.guyu.entity.Cart;
import com.guyu.entity.Slideshow;
import com.guyu.entity.Store;
import com.guyu.utils.Page;

import java.util.List;

public interface SlideshowService {

    /**
     *添加轮播图信息
     * @param slideshow
     * @return 状态码:1,成功;0,失败;
     */
    int AddSlideshow(Slideshow slideshow);

    /**
     * 分页查询
     * @param page
     * @return  数据列表
     */
    List<Slideshow> QuerySlideshow(Page page);

    /**
     * 删除轮播图信息
     * @param slideshow
     * @return 状态码:1,成功;0,失败;
     */
    int DelSlideshow(Slideshow slideshow);

    /**
     * 修改
     * @param slideshow
     * @return 状态码:1,成功;0,失败;
     */
    int UpdateSlideshowURl(Slideshow slideshow);

    /**
     * 批量删除轮播图信息
     * @param ids
     * @return 状态码:1,成功;0,失败;
     */
    Integer dels(List<Integer> ids);

    /**
     * 查询轮播图总条数
     * @return 总条数
     */
    Integer Total();

    /**
     * 修改轮播图状态
     * @param slideshow
     * @return
     */
    int UpdateStatus(Slideshow slideshow);

    /**
     * 查找启用的轮播图
     * @param slide_status
     * @return  Slideshow
     */
    Slideshow findSlideshow(Integer slide_status);
}
