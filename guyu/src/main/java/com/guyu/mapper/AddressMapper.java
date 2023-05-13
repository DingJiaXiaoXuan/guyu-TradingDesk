package com.guyu.mapper;

import com.guyu.entity.Address;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AddressMapper {

    /**
     * 添加收货地址
     * @param address
     * @return  状态码:1,成功;0,失败;
     */
    int addAddress(Address address);

    /**
     * 查询收货地址
     * @param user_id
     * @return  List<Address>
     */
    List<Address> QueryAddress(Integer user_id);

    /**
     * 删除收货地址
     * @param address_id
     * @return  状态码:1,成功;0,失败;
     */
    int DelAddress(Integer address_id);

    /**
     * 修改收货地址
     * @param address
     * @return 状态码:1,成功;0,失败;
     */
    int updataAddress(Address address);

    Address findAddress(Integer address_id);
}
