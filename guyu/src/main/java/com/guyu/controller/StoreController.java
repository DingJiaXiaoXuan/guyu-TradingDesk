package com.guyu.controller;

import com.guyu.entity.Store;
import com.guyu.service.StoreService;
import com.guyu.utils.Page;
import com.guyu.utils.StoreUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class StoreController {

    @Autowired
    StoreService storeService;

    /**
     *添加店铺信息
     * @param store
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/StoreAdd")
    public int StoreAdd(@RequestBody Store store){
        return storeService.StoreAdd(store);
    }

    /**
     * 分页查询
     * @param page
     * @return  数据列表
     */
    @RequestMapping("/StorePage")
    public Page StorePage(@RequestBody Page page){
        page.setStart();//起始
        page.setPoint();//终止
        page.setCountSun(storeService.Total());  //总条数
        page.setPageSun();  //总页数
        page.setData(storeService.StorePage(page));//数据
        return page;
    }

    /**
     * 分页查询(店铺审核)
     * @param  storeUtil
     * @return  数据列表
     */
    @RequestMapping("/StoreAudit")
    public Page QueryStoreAudit(@RequestBody StoreUtil storeUtil){
        storeUtil.getPage().setStart();//起始
        storeUtil.getPage().setPoint();//终止
        storeUtil.getPage().setCountSun(storeService.Total_status(storeUtil.getStore()));  //总条数
        storeUtil.getPage().setPageSun();  //总页数
        storeUtil.getPage().setData(storeService.QueryStoreAudit(storeUtil));//数据
        return storeUtil.getPage();
    }

    /**
     * 分页查询（店家）
     * @param storeUtil
     * @return Page
     */
    @RequestMapping("/QueryStoreUser")
    public Page QueryStoreUser(@RequestBody StoreUtil storeUtil){
        System.out.println(storeUtil.getStore().toString());
        storeUtil.getPage().setStart();//起始
        storeUtil.getPage().setPoint();//终止
        storeUtil.getPage().setCountSun(storeService.Total_user(storeUtil.getStore()));  //总条数
        storeUtil.getPage().setPageSun();  //总页数
        storeUtil.getPage().setData(storeService.QueryStoreUser(storeUtil));//数据
        return storeUtil.getPage();
    }

    /**
     * 删除店铺信息
     * @param store
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/StoreDel")
    public int StoreDel(@RequestBody Store store){
        return storeService.StoreDel(store.getStore_id());
    }

    /**
     * 修改店铺信息
     * @param store
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/StoreUpdate")
    public int StoreUpdate(@RequestBody Store store){
        return storeService.StoreUpdata(store);
    }

    /**
     * 批量删除
     * @param ids
     * @return  状态码
     */
    @PostMapping("/StoreDels")
    public int dels(@RequestBody List<Integer> ids){
        if (!ids.isEmpty()){
            return storeService.StoreDels(ids);
        }else {
            return 0;
        }
    }

    /**
     * 修改店铺状态信息
     * @param store
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/StoreStatus")
    public int StoreStatus(@RequestBody Store store){
        return storeService.UpdateStatus(store);
    }

    /**
     * 查询店铺信息
     * @param store
     * @return  Store
     */
    @RequestMapping("/FindStore")
    public Store findStore(@RequestBody Store store){
        return storeService.findStore(store);
    }

    /**
     * 查询用户名下店铺
     * @param store
     * @return List<Store>
     */
    @RequestMapping("/QueryUserStore")
    public List<Store> QueryUserStore(@RequestBody Store store){
        return storeService.QueryUserStore(store);
    }
}
