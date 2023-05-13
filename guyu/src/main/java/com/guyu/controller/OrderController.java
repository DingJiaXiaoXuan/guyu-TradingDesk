package com.guyu.controller;

import com.guyu.entity.Order;
import com.guyu.service.OrderService;
import com.guyu.utils.JsonResult;
import com.guyu.utils.OrderUtil;
import com.guyu.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    /**
     *添加订单信息
     * @param order
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/OrderAdd")
    public int OrderAdd(@RequestBody Order order){
        return orderService.AddOrder(order);
    }

    /**
     * 分页查询后端(管理员)
     * @param orderUtil
     * @return  数据列表
     */
    @RequestMapping("/OrderPage_1")
    public Page OrderPage1(@RequestBody OrderUtil orderUtil){
        orderUtil.getPage().setStart();//起始
        orderUtil.getPage().setPoint();//终止
        orderUtil.getPage().setCountSun(orderService.Total());  //总条数
        orderUtil.getPage().setPageSun();  //总页数
        orderUtil.getPage().setData(orderService.QueryOrder_1(orderUtil.getPage(),orderUtil.getOrder()));//数据
        return orderUtil.getPage();
    }


    /**
     * 分页查询后端(商家)
     * @param orderUtil
     * @return  数据列表
     */
    @RequestMapping("/OrderPage_2")
    public Page OrderPage2(@RequestBody OrderUtil orderUtil){
        orderUtil.getPage().setStart();//起始
        orderUtil.getPage().setPoint();//终止
        orderUtil.getPage().setCountSun(orderService.Total());  //总条数
        orderUtil.getPage().setPageSun();  //总页数
        orderUtil.getPage().setData(orderService.QueryOrder_2(orderUtil.getPage(),orderUtil.getOrder()));//数据
        return orderUtil.getPage();
    }

    /**
     * 分页查询后端(用户)
     * @param orderUtil
     * @return  数据列表
     */
    @RequestMapping("/OrderPage_3")
    public Page OrderPage3(@RequestBody OrderUtil orderUtil){
        orderUtil.getPage().setStart();//起始
        orderUtil.getPage().setPoint();//终止
        orderUtil.getPage().setCountSun(orderService.Total_order(orderUtil.getOrder()));  //总条数
        orderUtil.getPage().setPageSun();  //总页数
        orderUtil.getPage().setData(orderService.QueryOrder_3(orderUtil.getPage(),orderUtil.getOrder()));//数据
        return orderUtil.getPage();
    }

    /**
     * 删除订单信息
     * @param order
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/OrderDel")
    public int OrderDel(@RequestBody Order order){
        return orderService.DelOrder(order);
    }

    /**
     * 批量删除
     * @param ids
     * @return  状态码
     */
    @PostMapping("/OrderDels")
    public int dels(@RequestBody List<Integer> ids){
        if (!ids.isEmpty()){
            return orderService.dels(ids);
        }else {
            return 0;
        }
    }

    /**
     * 修改商品状态
     * @param order
     * @return 状态:1.成功;0.失败
     */
    @RequestMapping("/OrderStatus")
    public int OrderStatus(@RequestBody Order order){
        return orderService.UpdateStatus(order);
    }

    /**
     * 查询不同状态下的订单数量
     * @param order
     * @return
     */
    @RequestMapping("/OrderTotal")
    public Integer OrderTotal(@RequestBody Order order){
        return orderService.Total_order(order);
    }
}
