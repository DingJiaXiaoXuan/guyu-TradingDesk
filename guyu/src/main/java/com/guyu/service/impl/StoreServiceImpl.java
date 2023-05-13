package com.guyu.service.impl;

import com.guyu.entity.Store;
import com.guyu.mapper.ProductMapper;
import com.guyu.mapper.StoreMapper;
import com.guyu.service.StoreService;
import com.guyu.utils.Page;
import com.guyu.utils.StoreUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    StoreMapper storeMapper;

    @Autowired
    ProductMapper productMapper;

    /**
     * 创建店铺
     * @param store
     * @return 状态码：1.成功 0.失败
     */
    @Override
    public int StoreAdd(Store store) {
        store.setPro_num(0);
        store.setStore_status(0);
        return storeMapper.AddStore(store);
    }

    /**
     * 查询数据总数
     * @return  总数
     */
    @Override
    public Integer Total() {
        return storeMapper.Total();
    }

    /**
     * 查询店铺数量（商家）
     * @param store
     * @return
     */
    @Override
    public Integer Total_user(Store store) {
        return storeMapper.Total_user(store.getUser_id());
    }

    /**
     * 查询信息总条数(状态查询)
     * @param store
     * @return Integer
     */
    @Override
    public Integer Total_status(Store store) {
        return storeMapper.Total_status(store.getStore_status());
    }

    /**
     * 分页查询
     * @param   page
     * @return  数据
     */
    @Override
    public List<Store> StorePage(Page page) {
        return storeMapper.QueryStore(page.getStart(),page.getPoint());
    }

    /**
     * 查询用户名下店铺
     * @param store
     * @return List<Store>
     */
    @Override
    public List<Store> QueryUserStore(Store store) {
        return storeMapper.QueryUserStore(store.getUser_id());
    }

    /**
     * 分页查询（店铺审核）
     * @param storeUtil
     * @return List<Store>
     */
    @Override
    public List<Store> QueryStoreAudit(StoreUtil storeUtil) {
        return storeMapper.QueryStoreAudit(storeUtil.getPage().getStart(),storeUtil.getPage().getPoint(),storeUtil.getStore().getStore_status());
    }

    /**
     * 分页查询（商家）
     * @param storeUtil
     * @return List<Store>
     */
    @Override
    public List<Store> QueryStoreUser(StoreUtil storeUtil) {
        return storeMapper.QueryStoreUser(storeUtil.getPage().getStart(),storeUtil.getPage().getPoint(),storeUtil.getStore().getUser_id());
    }

    /**
     * 删除店铺
     * @param store_id
     * @return 状态码：1.成功 0.失败
     */
    @Override
    public int StoreDel(Integer store_id) {
        productMapper.DelProStore(store_id);
        return storeMapper.DelStore(store_id);
    }

    /**
     * 修改店铺信息
     * @param store
     * @return  状态码：1.成功 0.失败
     */
    @Override
    public int StoreUpdata(Store store) {
        storeMapper.UpdateTime(store.getStore_id());
        return storeMapper.UpdateStore(store);
    }

    /**
     * 批量删除
     * @param ids
     * @return  状态码：1.成功 0.失败
     */
    @Override
    public int StoreDels(List<Integer> ids) {
        return storeMapper.dels(ids);
    }

    /**
     * 修改状态
     * @param store
     * @return 状态码：1.成功 0.失败
     */
    @Override
    public int UpdateStatus(Store store) {
        storeMapper.UpdateTime(store.getStore_id());
        productMapper.updateStorePro(store.getStore_id());
        return storeMapper.UpdateStatus(store.getStore_status(),store.getStore_id());
    }

    @Override
    public Store findStore(Store store) {
        return storeMapper.findStore(store.getStore_id());
    }
}
