package com.guyu.controller;

import com.guyu.entity.Product;
import com.guyu.service.ProductService;
import com.guyu.utils.Page;
import com.guyu.utils.ProductUtil;
import com.guyu.utils.RecommendProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    /**
     *添加商品信息
     * @param product
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/ProAdd")
    public int ProAdd(@RequestBody Product product){
        return productService.ProAdd(product);
    }

    /**
     * 分页查询
     * @param page
     * @return  数据列表
     */
    @RequestMapping("/ProPage")
    public Page ProPage(@RequestBody Page page){
        page.setStart();//起始
        page.setPoint();//终止
        page.setCountSun(productService.Total());  //总条数
        page.setPageSun();  //总页数
        page.setData(productService.ProPage(page));//数据
        return page;
    }

    /**
     * 分页查询
     * @param page
     * @return  数据列表
     */
    @RequestMapping("/AllProPage")
    public Page AllProPage(@RequestBody Page page){
        page.setStart();//起始
        page.setPoint();//终止
        page.setCountSun(productService.Total_AllPro());  //总条数
        page.setPageSun();  //总页数
        page.setData(productService.AllProPage(page));//数据
        return page;
    }

    /**
     * 删除商品信息
     * @param product
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/ProDel")
    public int ProDel(@RequestBody Product product){
        return productService.ProDel(product.getPro_id());
    }

    /**
     * 修改商品信息
     * @param product
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/ProUpdate")
    public int ProUpdate(@RequestBody Product product){
        System.out.println(product.toString());
        return productService.ProUpdata(product);
    }

    /**
     * 批量删除
     * @param ids
     * @return  状态码:1,成功;0,失败;
     */
    @PostMapping("/ProDels")
    public int dels(@RequestBody List<Integer> ids){
        if (!ids.isEmpty()){
            return productService.ProDels(ids);
        }else {
            return 0;
        }
    }

    /**
     * 修改商品状态
     * @param product
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/ProStatus")
    public int ProStatus(@RequestBody Product product){
        return productService.UpdateStatus(product);
    }

    /**
     * 推荐商品
     * @return  推荐商品
     */
    @RequestMapping("/RecommendPro")
    public RecommendProduct RePro(){
        RecommendProduct list = new RecommendProduct();
        list.setData_1(productService.RecommendPro(10001));
        list.setData_2(productService.RecommendPro(10058));
        list.setData_3(productService.RecommendPro(10002));
        return list;
    }

    /**
     * 查找商品信息
     * @param product
     * @return Product
     */
    @RequestMapping("/findPro")
    public Product findPro(@RequestBody Product product){
        return productService.findProduct(product);
    }

    /**
     * 分页查询(所有商品分类查询)
     * @param productUtil
     * @return  数据列表
     */
    @RequestMapping("/CatePro")
    public Page CatePro(@RequestBody ProductUtil productUtil){
        productUtil.getPage().setStart();//起始
        productUtil.getPage().setPoint();//终止
        productUtil.getPage().setCountSun(productService.Total_cate(productUtil.getProduct()));  //总条数
        productUtil.getPage().setPageSun();  //总页数
        productUtil.getPage().setData(productService.ProCatePage(productUtil));//数据
        return productUtil.getPage();
    }

    /**
     * 分页查询(所有商品类别查询)
     * @param productUtil
     * @return  数据列表
     */
    @RequestMapping("/MainPro")
    public Page MainPro(@RequestBody ProductUtil productUtil){
        productUtil.getPage().setStart();//起始
        productUtil.getPage().setPoint();//终止
        productUtil.getPage().setCountSun(productService.Total());  //总条数
        productUtil.getPage().setPageSun();  //总页数
        productUtil.getPage().setData(productService.ProMainPage(productUtil));//数据
        return productUtil.getPage();
    }

    /**
     * 分页查询(所有商品类别查询)
     * @param productUtil
     * @return  数据列表
     */
    @RequestMapping("/ProductAudit")
    public Page QueryProductAudit(@RequestBody ProductUtil productUtil){
        productUtil.getPage().setStart();//起始
        productUtil.getPage().setPoint();//终止
        productUtil.getPage().setCountSun(productService.Total_status(productUtil.getProduct()));  //总条数
        productUtil.getPage().setPageSun();  //总页数
        productUtil.getPage().setData(productService.QueryProductAudit(productUtil));//数据
        return productUtil.getPage();
    }

    /**
     * 分页查询(商店查询)
     * @param productUtil
     * @return  数据列表
     */
    @RequestMapping("/StorePro")
    public Page ProStorePage(@RequestBody ProductUtil productUtil){
        productUtil.getPage().setStart();//起始
        productUtil.getPage().setPoint();//终止
        productUtil.getPage().setCountSun(productService.Total_store(productUtil.getProduct()));  //总条数
        productUtil.getPage().setPageSun();  //总页数
        productUtil.getPage().setData(productService.ProStorePage(productUtil));//数据
        return productUtil.getPage();
    }

    /**
     * 分页查询(商店查询)
     * @param productUtil
     * @return  数据列表
     */
    @RequestMapping("/UserPro")
    public Page ProUserPage(@RequestBody ProductUtil productUtil){
        productUtil.getPage().setStart();//起始
        productUtil.getPage().setPoint();//终止
        productUtil.getPage().setCountSun(productService.Total_user(productUtil.getProduct()));  //总条数
        productUtil.getPage().setPageSun();  //总页数
        productUtil.getPage().setData(productService.ProUserPage(productUtil));//数据
        return productUtil.getPage();
    }
}
