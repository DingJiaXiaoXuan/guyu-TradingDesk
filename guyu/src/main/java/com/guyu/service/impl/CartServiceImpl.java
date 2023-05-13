package com.guyu.service.impl;

import com.guyu.entity.Cart;
import com.guyu.mapper.CartMapper;
import com.guyu.mapper.CategoryMapper;
import com.guyu.service.CartService;
import com.guyu.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartMapper cartMapper;

    @Override
    public int AddCart(Cart cart) {
        if(cartMapper.findCart(cart)==0){
            return cartMapper.AddCart(cart);
        }
        return 2;
    }

    @Override
    public List<Cart> QueryCart(Cart cart) {
        return cartMapper.QueryCart(cart.getUser_id());
    }

    @Override
    public int DelCart(Cart cart) {
        return cartMapper.DelCart(cart.getCart_id());
    }

    @Override
    public int UpdateCartNum(Cart cart) {
        cartMapper.UpdateTime(cart.getCart_id());
        return cartMapper.UpdateCartNum(cart);
    }

    @Override
    public Integer dels(List<Integer> ids) {
        return cartMapper.dels(ids);
    }

    @Override
    public Integer Total() {
        return cartMapper.Total();
    }

}
