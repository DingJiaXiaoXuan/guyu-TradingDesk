package com.guyu.mapper;

import com.guyu.entity.Order;
import com.guyu.entity.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderMapper {

    /**
     *添加订单信息
     * @param order
     * @return 状态码:1,成功;0,失败;
     */
    int AddOrder(Order order);

    /**
     * 分页查询后端(管理员)
     * @param start
     * @param point
     * @return  数据列表
     */
    List<Order> QueryOrder(@Param("start") Integer start, @Param("point") Integer point);

    /**
     * 分页查询后端(管理员)
     * @param start
     * @param point
     * @return  数据列表
     */
    List<Order> QueryOrder_1(@Param("start") Integer start, @Param("point") Integer point, @Param("ord_status") Integer ord_status);

    /**
     * 分页查询后端(商家)
     * @param start
     * @param point
     * @param store_id
     * @return  数据列表
     */
    List<Order> QueryOrder_2(@Param("start") Integer start, @Param("point") Integer point,@Param("store_id") Integer store_id,@Param("ord_status") Integer ord_status);

    /**
     * 分页查询后端(用户)
     * @param start
     * @param point
     * @param user_id
     * @return  数据列表
     */
    List<Order> QueryOrder_3(@Param("start") Integer start, @Param("point") Integer point,@Param("user_id") Integer user_id,@Param("ord_status") Integer ord_status);

    /**
     * 分页查询(用户,全部订单)
     * @param start
     * @param point
     * @param user_id
     * @return  数据列表
     */
    List<Order> QueryOrder_4(@Param("start") Integer start, @Param("point") Integer point,@Param("user_id") Integer user_id);

    /**
     * 删除订单信息
     * @param ord_id
     * @return 状态码:1,成功;0,失败;
     */
    int DelOrder(Integer ord_id);

    /**
     * 批量删除店铺信息
     * @param ids
     * @return 状态码:1,成功;0,失败;
     */
    Integer dels(List<Integer> ids);

    /**
     * 模糊查询
     * @param order
     * @return 所查询数据
     */
    List<Order> FuzzyQuery(Order order);

    /**
     * 查询订单信息总条数
     * @return 总条数
     */
    Integer Total();


    /**
     * 更新时间
     * @param ord_id
     */
    void UpdateTime(Integer ord_id);

    /**
     * 修改商品状态
     * @param ord_id
     * @param ord_status
     * @return 状态:1.成功;0.失败
     */
    int UpdateStatus(@Param("ord_status") int ord_status,@Param("ord_id") Integer ord_id);

    /**
     * 查询不同状态的订单数量
     * @param ord_status
     * @return  数量
     */
    Integer Total_order(@Param("ord_status") int ord_status,@Param("user_id") Integer user_id);
}
