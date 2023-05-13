package com.guyu.service.impl;

import com.guyu.entity.Product;
import com.guyu.mapper.ProductMapper;
import com.guyu.mapper.StoreMapper;
import com.guyu.service.ProductService;
import com.guyu.service.StoreService;
import com.guyu.utils.Page;
import com.guyu.utils.ProductUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    StoreMapper storeMapper;

    /**
     *添加商品信息
     * @param product
     * @return 状态码:1,成功;0,失败;
     */
    @Override
    public int ProAdd(Product product) {
        Integer pro_num;
        pro_num=storeMapper.findPro_num(product.getStore_id());
        storeMapper.updatePro_num(pro_num+1,product.getStore_id());
        return productMapper.AddProduct(product);
    }

    /**
     * 查询信息总条数
     * @return  总条数
     */
    @Override
    public Integer Total() {
        return productMapper.Total();
    }

    /**
     * 查询信息总条数(分类查询）
     * @return  总条数
     */
    @Override
    public Integer Total_cate(Product product) {
        return productMapper.Total_cate(product.getCate_id());
    }

    /**
     * 查询商品信息总条数（前端）
     * @return  总条数
     */
    @Override
    public Integer Total_AllPro() {
        return productMapper.Total_AllPro();
    }

    /**
     * 查询信息总条数(店铺查询）
     * @return  总条数
     */
    @Override
    public Integer Total_store(Product product) {
        return productMapper.Total_store(product.getStore_id());
    }

    /**
     * 查询信息总条数(用户查询）
     * @return  总条数
     */
    @Override
    public Integer Total_user(Product product) {
        return productMapper.Total_user(product.getUser_id());
    }

    /**
     * 查询信息总条数(状态查询）
     * @return  总条数
     */
    @Override
    public Integer Total_status(Product product) {
        return productMapper.Total_status(product.getPro_status());
    }

    /**
     * 分页查询
     * @param page
     * @return 数据
     */
    @Override
    public List<Product> ProPage(Page page) {
        return productMapper.QueryProduct(page.getStart(),page.getPoint());
    }

    /**
     * 分页查询(前端)
     * @param page
     * @return 数据
     */
    @Override
    public List<Product> AllProPage(Page page) {
        return productMapper.QueryAllProduct(page.getStart(),page.getPoint());
    }

    /**
     * 推荐商品查询
     * @param cate_id
     * @return  推荐的商品
     */
    @Override
    public List<Product> RecommendPro(Integer cate_id) {
        return productMapper.RecommendProduct(cate_id);
    }

    /**
     * 删除商品信息
     * @param pro_id
     * @return 状态码:1,成功;0,失败;
     */
    @Override
    public int ProDel(Integer pro_id) {
        Integer pro_num;
        Integer store_id;
        store_id=productMapper.findStore_id(pro_id);
        pro_num=storeMapper.findPro_num(store_id);
        storeMapper.updatePro_num(pro_num-1,store_id);
        return productMapper.DelProduct(pro_id);
    }

    /**
     * 修改商品信息
     * @param product
     * @return 状态码:1,成功;0,失败;
     */
    @Override
    public int ProUpdata(Product product) {
        productMapper.UpdateTime(product.getPro_id());
        return productMapper.UpdateProduct(product);
    }

    /**
     * 批量删除
     * @param ids
     * @return  状态码:1,成功;0,失败;
     */
    @Override
    public int ProDels(List<Integer> ids) {
        Integer pro_num;
        Integer store_id;
        for (int i = 0; i < ids.size(); i++) {
            store_id=productMapper.findStore_id(ids.get(i));
            pro_num=storeMapper.findPro_num(store_id);
            storeMapper.updatePro_num(pro_num-1,store_id);
        }
        return productMapper.dels(ids);
    }
    /**
     * 修改商品状态
     * @param product
     * @return  状态码:1,成功;0,失败;
     */
    @Override
    public int UpdateStatus(Product product) {
        productMapper.UpdateTime(product.getPro_id());
        return productMapper.UpdateStatus(product.getPro_status(),product.getPro_id());
    }

    /**
     * 查找商品信息
     * @param product
     * @return Product
     */
    @Override
    public Product findProduct(Product product) {
        return productMapper.findProduct(product.getPro_id());
    }

    /**
     * 分页查询（所有分类查询）
     * @param productUtil
     * @return List<Product>
     */
    @Override
    public List<Product> ProCatePage(ProductUtil productUtil) {
        return productMapper.CateProduct(productUtil.getPage().getStart(),productUtil.getPage().getPoint(),productUtil.getProduct().getCate_id());
    }

    /**
     * 分页查询(所有商品类别查询)
     * @param productUtil
     * @return  数据列表
     */
    @Override
    public List<Product> ProMainPage(ProductUtil productUtil) {
        return productMapper.MainProduct(productUtil.getPage().getStart(),productUtil.getPage().getPoint(),productUtil.getCateMain().getMain_id());
    }

    /**
     * 分页查询(商店查询)
     * @param productUtil
     * @return  数据列表
     */
    @Override
    public List<Product> ProStorePage(ProductUtil productUtil) {
        return productMapper.StoreProduct(productUtil.getPage().getStart(),productUtil.getPage().getPoint(),productUtil.getProduct().getStore_id());
    }

    /**
     * 分页查询(用户查询)
     * @param productUtil
     * @return  数据列表
     */
    @Override
    public List<Product> ProUserPage(ProductUtil productUtil) {
        return productMapper.UserProduct(productUtil.getPage().getStart(),productUtil.getPage().getPoint(),productUtil.getProduct().getUser_id());
    }

    /**
     * 分页查询(商品审核)
     * @param productUtil
     * @return List<Product>
     */
    @Override
    public List<Product> QueryProductAudit(ProductUtil productUtil) {
        return productMapper.QueryProductAudit(productUtil.getPage().getStart(),productUtil.getPage().getPoint(),productUtil.getProduct().getPro_status());
    }


}
