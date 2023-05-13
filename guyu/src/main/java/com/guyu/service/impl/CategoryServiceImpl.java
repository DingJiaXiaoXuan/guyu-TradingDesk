package com.guyu.service.impl;

import com.guyu.entity.CateMain;
import com.guyu.entity.Category;
import com.guyu.mapper.CategoryMapper;
import com.guyu.service.CategoryService;
import com.guyu.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    /* 二级分类*/
    @Override
    public int CateAdd(Category category) {
        return categoryMapper.AddCate(category);
    }

    @Override
    public Integer CateTotal() {
        return categoryMapper.CateTotal();
    }

    @Override
    public List<Category> CatePage(Page page) {
        return categoryMapper.QueryCate(page.getStart(),page.getPoint());
    }

    @Override
    public int CateDel(Integer Cate_id) {
        return categoryMapper.DelCate(Cate_id);
    }

    @Override
    public int CateUpdata(Category category) {
        categoryMapper.CateUpdateTime(category.getCate_id());
        return categoryMapper.UpdateCate(category);
    }

    @Override
    public int CateDels(List<Integer> ids) {
        return categoryMapper.CateDels(ids);
    }

    @Override
    public int CateUpdateStatus(Category category) {
        categoryMapper.CateUpdateTime(category.getCate_id());
        return categoryMapper.CateUpdateStatus(category.getCate_status(),category.getCate_id());
    }

    @Override
    public List<Category> findCate(Category category) {
        return categoryMapper.findCate(category.getMain_id());
    }

    /* 一级分类*/

    @Override
    public int CateMainAdd(CateMain cateMain) {
        return categoryMapper.AddCateMain(cateMain);
    }

    @Override
    public Integer CateMainTotal() {
        return categoryMapper.CateMainTotal();
    }

    @Override
    public List<CateMain> CateMainPage(Page page) {
        return categoryMapper.QueryCateMain(page.getStart(),page.getPoint());
    }

    @Override
    public int CateMainDel(Integer main_id) {
        return categoryMapper.DelCateMain(main_id);
    }

    @Override
    public int CateMainUpdata(CateMain cateMain) {
        categoryMapper.CateMainUpdateTime(cateMain.getMain_id());
        return categoryMapper.UpdateCateMain(cateMain);
    }

    @Override
    public int CateMainDels(List<Integer> ids) {
        return categoryMapper.CateMainDels(ids);
    }

    @Override
    public int CateMainUpdateStatus(CateMain cateMain) {
        categoryMapper.CateMainUpdateTime(cateMain.getMain_id());
        return categoryMapper.CateMainUpdateStatus(cateMain.getMain_status(),cateMain.getMain_id());
    }

    @Override
    public List<CateMain> findCateMain() {
        return categoryMapper.findCateMain();
    }

    //选择器数据
    @Override
    public List<CateMain> CateSelect() {
        return categoryMapper.CateSelect();
    }

    @Override
    public CateMain inquire_main(Category category) {
        Integer main_id;
        main_id=categoryMapper.inquire_main_id(category.getCate_id());
        return categoryMapper.inquire_main(main_id);
    }

    @Override
    public Category inquire_cate(Category category) {
        return categoryMapper.inquire_cate(category.getCate_id());
    }
}
