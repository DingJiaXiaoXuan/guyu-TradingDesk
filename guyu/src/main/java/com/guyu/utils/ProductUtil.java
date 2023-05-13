package com.guyu.utils;

import com.guyu.entity.CateMain;
import com.guyu.entity.Product;
import lombok.Data;

@Data
public class ProductUtil {

    private Page page;        //分页查询
    private Product product;  //商品信息
    private CateMain cateMain;  //主类别信息
}
