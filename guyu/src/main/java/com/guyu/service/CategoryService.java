package com.guyu.service;


import com.guyu.entity.CateMain;
import com.guyu.entity.Category;
import com.guyu.entity.Product;
import com.guyu.utils.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    int CateAdd(Category category);

    Integer CateTotal();

    List<Category> CatePage(Page page);

    int CateDel(Integer Cate_id);

    int CateUpdata(Category category);

    int CateDels(List<Integer> ids);

    int CateUpdateStatus(Category category);

    List<Category>findCate(Category category);




    int CateMainAdd(CateMain cateMain);

    Integer CateMainTotal();

    List<CateMain> CateMainPage(Page page);

    int CateMainDel(Integer main_id);

    int CateMainUpdata(CateMain cateMain);

    int CateMainDels(List<Integer> ids);

    int CateMainUpdateStatus(CateMain cateMain);

    List<CateMain>findCateMain();

    List<CateMain> CateSelect();

    CateMain inquire_main(Category category);

    Category inquire_cate(Category category);
}
