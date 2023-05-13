package com.guyu.service;

import com.guyu.entity.Address;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {

    int addAddress(Address address);

    List<Address> QueryAddress(Address address);

    int DelAddress(Address address);

    int updataAddress(Address address);

    Address findAddress(Address address);
}
