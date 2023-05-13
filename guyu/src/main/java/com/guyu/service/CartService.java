package com.guyu.service;

import com.guyu.entity.Cart;
import com.guyu.utils.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
    /**
     *添加购物车信息
     * @param cart
     * @return 状态码:1,成功;0,失败;
     */
    int AddCart(Cart cart);

    /**
     * 分页查询
     * @param cart
     * @return  数据列表
     */
    List<Cart> QueryCart(Cart cart);

    /**
     * 删除购物车信息
     * @param cart
     * @return 状态码:1,成功;0,失败;
     */
    int DelCart(Cart cart);

    /**
     * 修改购物车数量
     * @param cart
     * @return 状态码:1,成功;0,失败;
     */
    int UpdateCartNum(Cart cart);

    /**
     * 批量删除购物车信息
     * @param ids
     * @return 状态码:1,成功;0,失败;
     */
    Integer dels(List<Integer> ids);

    /**
     * 查询用户信息总条数
     * @return 总条数
     */
    Integer Total();
}
