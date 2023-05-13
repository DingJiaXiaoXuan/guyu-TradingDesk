package com.guyu.controller;

import com.guyu.entity.Cart;
import com.guyu.service.CartService;
import com.guyu.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CartController {

    @Autowired
    CartService cartService;

    /**
     *添加购物车信息
     * @param cart
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/AddCart")
    public int AddCart(@RequestBody Cart cart){
        return cartService.AddCart(cart);
    }

    /**
     * 查询购物车
     * @param cart
     * @return  数据列表
     */
    @RequestMapping("/CartPage")
    public List<Cart> CartPage(@RequestBody Cart cart){
        return cartService.QueryCart(cart);
    }

    /**
     * 删除购物车信息
     * @param cart
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/DelCart")
    public int DelCart(@RequestBody Cart cart){
        return cartService.DelCart(cart);
    }

    /**
     * 修改购物车数量
     * @param cart
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/UpdateCartNum")
    public int UpdateCartNum(@RequestBody Cart cart){
        return cartService.UpdateCartNum(cart);
    }

    /**
     * 批量删除购物车信息
     * @param ids
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/CartDels")
    public  Integer dels(@RequestBody List<Integer> ids){
        if (!ids.isEmpty()){
            return cartService.dels(ids);
        }else {
            return 0;
        }
    }

}
