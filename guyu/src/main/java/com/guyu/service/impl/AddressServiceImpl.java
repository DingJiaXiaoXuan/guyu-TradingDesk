package com.guyu.service.impl;

import com.guyu.entity.Address;
import com.guyu.mapper.AddressMapper;
import com.guyu.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressMapper addressMapper;


    /**
     * 添加收货地址
     * @param address
     * @return 状态码:1,成功;0,失败;
     */
    @Override
    public int addAddress(Address address) {
        return addressMapper.addAddress(address);
    }

    /**
     * 查询收货地址
     * @param address
     * @return List<Address>
     */
    @Override
    public List<Address> QueryAddress(Address address) {
        return addressMapper.QueryAddress(address.getUser_id());
    }

    /**
     * 删除收货地址
     * @param address
     * @return 状态码:1,成功;0,失败;
     */
    @Override
    public int DelAddress(Address address) {
        return addressMapper.DelAddress(address.getAddress_id());
    }

    /**
     * 修改收货地址
     * @param address
     * @return 状态码:1,成功;0,失败;
     */
    @Override
    public int updataAddress(Address address) {
        return addressMapper.updataAddress(address);
    }

    /**
     * 查询收货地址
     * @param address
     * @return Address
     */
    @Override
    public Address findAddress(Address address) {
        return addressMapper.findAddress(address.getAddress_id());
    }
}
