package com.guyu.mapper;

import com.guyu.entity.Review;
import com.guyu.entity.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ReviewMapper {

    /**
     *添加评论信息
     * @param review
     * @return 状态码:1,成功;0,失败;
     */
    int AddReview(Review review);

    /**
     * 分页查询
     * @param start
     * @param point
     * @return  数据列表
     */
    List<Review> QueryReview(@Param("start") Integer start, @Param("point") Integer point);

    /**
     * 前台评论展示
     * @param review
     * @return  List<Review>
     */
    List<Review> findReview(Review review);

    /**
     * 删除评论信息
     * @param review_id
     * @return 状态码:1,成功;0,失败;
     */
    int DelReview(Integer review_id);

    /**
     * 修改评论信息
     * @param review
     * @return 状态码:1,成功;0,失败;
     */
    int UpdateReview(Review review);

    /**
     * 批量删除评论信息
     * @param ids
     * @return 状态码:1,成功;0,失败;
     */
    Integer dels(List<Integer> ids);

    /**
     * 查询用户信息总条数
     * @return 总条数
     */
    Integer Total();

    /**
     * 更新时间
     * @param review_id
     */
    void UpdateTime(Integer review_id);

    /**
     * 修改商品状态
     * @param review_id
     * @param review_status
     * @return 状态:1.成功;0.失败
     */
    int UpdateStatus(@Param("review_status") int review_status,@Param("review_id") Integer review_id);
}
