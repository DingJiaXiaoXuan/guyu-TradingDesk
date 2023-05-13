package com.guyu.mapper;

import com.guyu.entity.Review;
import com.guyu.entity.Slideshow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SlideshowMapper {

    /**
     *添加轮播图信息
     * @param slideshow
     * @return 状态码:1,成功;0,失败;
     */
    int AddSlideshow(Slideshow slideshow);

    /**
     * 查找启用的轮播图
     * @param slide_status
     * @return  Slideshow
     */
    Slideshow findSlideshow(Integer slide_status);

    /**
     * 分页查询
     * @param start
     * @param point
     * @return  数据列表
     */
    List<Slideshow> QuerySlideshow(@Param("start") Integer start, @Param("point") Integer point);

    /**
     * 删除轮播图信息
     * @param slide_id
     * @return 状态码:1,成功;0,失败;
     */
    int DelSlideshow(Integer slide_id);

    /**
     * 修改轮播图URL
     * @param slideshow
     * @return 状态码:1,成功;0,失败;
     */
    int UpdateSlideshowURl(Slideshow slideshow);

    /**
     * 批量删除轮播图
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
     * 更新时间
     * @param slide_id
     */
    void UpdateTime(Integer slide_id);

    /**
     * 修改轮播图状态码
     * @param slide_id
     * @param slide_status
     * @return 状态:1.成功;0.失败
     */
    int UpdateStatus(@Param("slide_status") int slide_status, @Param("slide_id") Integer slide_id);
}
