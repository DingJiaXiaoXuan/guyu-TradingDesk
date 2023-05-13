package com.guyu.service;

import com.guyu.entity.Store;
import com.guyu.utils.Page;
import com.guyu.utils.StoreUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StoreService {

    int StoreAdd(Store store);

    Integer Total();

    Integer Total_user(Store store);

    Integer Total_status(Store store);

    List<Store> StorePage(Page page);

    List<Store> QueryUserStore(Store store);

    List<Store> QueryStoreAudit(StoreUtil storeUtil);

    List<Store> QueryStoreUser(StoreUtil storeUtil);

    int StoreDel(Integer store_id);

    int StoreUpdata(Store store);

    int StoreDels(List<Integer> ids);

    int UpdateStatus(Store store);

    Store findStore(Store store);


}
