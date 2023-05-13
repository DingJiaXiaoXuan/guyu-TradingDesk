package com.guyu.service;

import com.guyu.entity.Order;
import com.guyu.utils.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    /**
     *添加订单信息
     * @param order
     * @return 状态码:1,成功;0,失败;
     */
    int AddOrder(Order order);

    /**
     * 分页查询后端(管理员)
     * @param page
     * @return  数据列表
     */
    List<Order> QueryOrder_1(Page page,Order order);

    /**
     * 分页查询后端(商家)
     * @param page
     * @param order
     * @return  数据列表
     */
    List<Order> QueryOrder_2(Page page,Order order);

    /**
     * 分页查询后端(用户)
     * @param page
     * @param order
     * @return  数据列表
     */
    List<Order> QueryOrder_3(Page page,Order order);

    /**
     * 删除订单信息
     * @param order
     * @return 状态码:1,成功;0,失败;
     */
    int DelOrder(Order order);

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
     * 修改商品状态
     * @param order
     * @return 状态:1.成功;0.失败
     */
    int UpdateStatus(Order order);

    /**
     * 查询用户信息总条数
     * @return 总条数
     */
    Integer Total();


    /**
     * 查询不同状态的订单数量
     * @param order
     * @return  数量
     */
    Integer Total_order(Order order);
}
