package com.guyu.mapper;

import com.guyu.entity.Cart;
import com.guyu.entity.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CartMapper {
    /**
     *添加购物车信息
     * @param cart
     * @return 状态码:1,成功;0,失败;
     */
    int AddCart(Cart cart);

    /**
     * 查询购物车
     * @param user_id
     * @return  数据列表
     */
    List<Cart> QueryCart(Integer user_id);

    /**
     * 查找此商品是否存在
     * @param cart
     * @return cart
     */
    int findCart(Cart cart);

    /**
     * 删除购物车信息
     * @param cart_id
     * @return 状态码:1,成功;0,失败;
     */
    int DelCart(Integer cart_id);

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

    /**
     * 更新时间
     * @param cart_id
     */
    int UpdateTime(Integer cart_id);

}
