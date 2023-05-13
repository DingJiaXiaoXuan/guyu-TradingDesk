package com.guyu.controller;

import com.guyu.entity.CateMain;
import com.guyu.entity.Category;
import com.guyu.service.CategoryService;
import com.guyu.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    /**
     * 分页查询(一级分类)
     * @param page
     * @return  数据列表
     */
    @RequestMapping("/CateMainPage")
    public Page CateMainPage(@RequestBody Page page){
        page.setStart();//起始
        page.setPoint();//终止
        page.setCountSun(categoryService.CateMainTotal());  //总条数
        page.setPageSun();  //总页数
        page.setData(categoryService.CateMainPage(page));//数据
        return page;
    }

    /**
     * 分页查询(二级分类)
     * @param page
     * @return  数据列表
     */
    @RequestMapping("/CatePage")
    public Page CatePage(@RequestBody Page page){
        page.setStart();//起始
        page.setPoint();//终止
        page.setCountSun(categoryService.CateTotal());  //总条数
        page.setPageSun();  //总页数
        page.setData(categoryService.CatePage(page));//数据
        return page;
    }

    /**
     *添加一级种类信息
     * @param cateMain
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/CateMainAdd")
    public int CateMainAdd(@RequestBody CateMain cateMain){
        return categoryService.CateMainAdd(cateMain);
    }

    /**
     *添加二级种类信息
     * @param category
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/CateAdd")
    public int CateAdd(@RequestBody Category category){
       // System.out.println(category.toString());
        return categoryService.CateAdd(category);
    }

    /**
     * 删除一级分类信息
     * @param cateMain
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/CateMainDel")
    public int CateMainDel(@RequestBody CateMain cateMain){
        return categoryService.CateMainDel(cateMain.getMain_id());
    }

    /**
     * 删除二级分类信息
     * @param category
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/CateDel")
    public int CateDel(@RequestBody Category category){
        return categoryService.CateDel(category.getCate_id());
    }

    /**
     * 修改一级分类信息
     * @param cateMain
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/CateMainUpdate")
    public int CateMainUpdate(@RequestBody CateMain cateMain){
        System.out.println(cateMain.toString());
        return categoryService.CateMainUpdata(cateMain);
    }

    /**
     * 修改二级分类信息
     * @param category
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/CateUpdate")
    public int CateUpdate(@RequestBody Category category){
        System.out.println(category.toString());
        return categoryService.CateUpdata(category);
    }

    /**
     * 批量删除一级分类
     * @param ids
     * @return  状态码:1,成功;0,失败;
     */
    @PostMapping("/CateMainDels")
    public int CateMaindels(@RequestBody List<Integer> ids){
        if (!ids.isEmpty()){
            return categoryService.CateMainDels(ids);
        }else {
            return 0;
        }
    }

    /**
     * 批量删除二级分类
     * @param ids
     * @return  状态码:1,成功;0,失败;
     */
    @PostMapping("/CateDels")
    public int Catedels(@RequestBody List<Integer> ids){
        if (!ids.isEmpty()){
            return categoryService.CateDels(ids);
        }else {
            return 0;
        }
    }

    /**
     * 修改一级分类状态
     * @param cateMain
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/CateMainStatus")
    public int CateMainStatus(@RequestBody CateMain cateMain){
        return categoryService.CateMainUpdateStatus(cateMain);
    }

    /**
     * 修改二级分类状态
     * @param category
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/CateStatus")
    public int CateStatus(@RequestBody Category category){
        return categoryService.CateUpdateStatus(category);
    }

    /**
     * 选择器
     * @return
     */
    @RequestMapping("/CateSelect")
    public List<CateMain> CateSelect(){
        return categoryService.CateSelect();
    }

    /**
     * 查询所有类别
     * @return List<CateMain>
     */
    @RequestMapping("/FindCateMain")
    public List<CateMain> FindCate(){
        return categoryService.findCateMain();
    }

    /**
     * 查询所有种类
     * @return  List<Category>
     */
    @RequestMapping("/FindCate")
    public List<Category> FindCateMain(@RequestBody Category category){
        return categoryService.findCate(category);
    }

    /**
     * 根据种类编号查询种类
     * @param category
     * @return Category
     */
    @RequestMapping("/inquire_cate")
    public Category inquire_cate(@RequestBody Category category){
        return categoryService.inquire_cate(category);
    }

    /**
     * 根据种类编号查询分类
     * @param category
     * @return CateMain
     */
    @RequestMapping("/inquire_main")
    public CateMain inquire_main(@RequestBody Category category){
        return categoryService.inquire_main(category);
    }
}
