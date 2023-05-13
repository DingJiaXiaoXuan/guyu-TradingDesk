package com.guyu.service;

import com.guyu.entity.Collect;
import com.guyu.utils.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CollectService {

    int AddCollect(Collect collect);

    List<Collect> QueryCollect(Collect collect);

    Integer Total(Collect collect);

    int DelCollect(Collect collect);

    Integer dels(List<Integer> ids);
}
