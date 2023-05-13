package com.guyu.utils;

import com.guyu.entity.Product;
import lombok.Data;

import java.util.List;

@Data
public class RecommendProduct {
    private List<Product> data_1;
    private List<Product> data_2;
    private List<Product> data_3;
}
