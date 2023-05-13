package com.guyu.service;


import com.guyu.entity.Product;
import com.guyu.utils.Page;
import com.guyu.utils.ProductUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    int ProAdd(Product product);

    Integer Total();

    Integer Total_cate(Product product);

    Integer Total_AllPro();

    Integer Total_status(Product product);

    Integer Total_store(Product product);

    Integer Total_user(Product product);

    List<Product> ProPage(Page page);

    List<Product> AllProPage(Page page);

    List<Product> RecommendPro(Integer cate_id);

    int ProDel(Integer pro_id);

    int ProUpdata(Product product);

    int ProDels(List<Integer> ids);

    int UpdateStatus(Product product);

    Product findProduct(Product product);

    List<Product> ProCatePage(ProductUtil productUtil);

    List<Product> ProMainPage(ProductUtil productUtil);

    List<Product> ProStorePage(ProductUtil productUtil);

    List<Product> ProUserPage(ProductUtil productUtil);

    List<Product> QueryProductAudit(ProductUtil productUtil);
}
