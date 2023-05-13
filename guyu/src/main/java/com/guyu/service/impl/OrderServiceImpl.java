package com.guyu.service.impl;

import com.guyu.entity.Order;
import com.guyu.mapper.OrderMapper;
import com.guyu.service.OrderService;
import com.guyu.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Override
    public int AddOrder(Order order) {
        return orderMapper.AddOrder(order);
    }

    @Override
    public List<Order> QueryOrder_1(Page page,Order order) {
        if(order.getOrd_status()==-1)
        {
            return orderMapper.QueryOrder(page.getStart(),page.getPoint());
        }
        return orderMapper.QueryOrder_1(page.getStart(),page.getPoint(),order.getOrd_status());
    }

    @Override
    public List<Order> QueryOrder_2(Page page, Order order) {
        return orderMapper.QueryOrder_2(page.getStart(),page.getPoint(),order.getStore_id(),order.getOrd_status());
    }

    @Override
    public List<Order> QueryOrder_3(Page page, Order order) {
        if(order.getOrd_status()==-1)
        {
            return orderMapper.QueryOrder_4(page.getStart(),page.getPoint(),order.getUser_id());
        }
        return orderMapper.QueryOrder_3(page.getStart(),page.getPoint(),order.getUser_id(),order.getOrd_status());
    }

    @Override
    public int DelOrder(Order order) {
        return orderMapper.DelOrder(order.getOrd_id());
    }

    @Override
    public Integer dels(List<Integer> ids) {
        return orderMapper.dels(ids);
    }

    @Override
    public List<Order> FuzzyQuery(Order order) {
        return orderMapper.FuzzyQuery(order);
    }


    @Override
    public int UpdateStatus(Order order) {
        orderMapper.UpdateTime(order.getOrd_id());
        return orderMapper.UpdateStatus(order.getOrd_status(),order.getOrd_id());
    }

    @Override
    public Integer Total() {
        return orderMapper.Total();
    }


    @Override
    public Integer Total_order(Order order) {
        return orderMapper.Total_order(order.getOrd_status(),order.getUser_id());
    }
}
