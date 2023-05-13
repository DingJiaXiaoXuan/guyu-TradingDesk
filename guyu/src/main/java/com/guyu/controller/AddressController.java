package com.guyu.controller;


import com.guyu.entity.Address;
import com.guyu.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AddressController {

    @Autowired
    AddressService addressService;

    /**
     * 添加收货地址
     * @param address
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/addAddress")
    public int addAddress(@RequestBody Address address){
        return addressService.addAddress(address);
    }

    /**
     * 查询收货地址
     * @param address
     * @return List<Address>
     */
    @RequestMapping("/QueryAddress")
    public List<Address> QueryAddress(@RequestBody Address address){
        return addressService.QueryAddress(address);
    }

    /**
     * 删除收货地址
     * @param address
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/DelAddress")
    public int DelAddress(@RequestBody Address address){
        return addressService.DelAddress(address);
    }

    /**
     * 修改收货地址
     * @param address
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/updataAddress")
    public int updataAddress(@RequestBody Address address){
        return addressService.updataAddress(address);
    }

    /**
     * 查询收货地址
     * @param address
     * @return Address
     */
    @RequestMapping("/findAddress")
    public Address findAddress(@RequestBody Address address){
        return addressService.findAddress(address);
    }
}
