package com.guyu.service.impl;

import com.guyu.entity.Collect;
import com.guyu.mapper.CollectMapper;
import com.guyu.service.CollectService;
import com.guyu.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    CollectMapper collectMapper;


    @Override
    public int AddCollect(Collect collect) {
        if (StringUtils.isEmpty(collectMapper.FindPro_id(collect.getPro_id()))){
            return collectMapper.AddCollect(collect);
        }
        return 2;
    }

    @Override
    public List<Collect> QueryCollect( Collect collect) {
        return collectMapper.QueryCollect(collect.getUser_id());
    }

    @Override
    public Integer Total(Collect collect) {
        return collectMapper.Total(collect.getUser_id());
    }

    @Override
    public int DelCollect(Collect collect) {
        return collectMapper.DelCollect(collect.getCollect_id());
    }

    @Override
    public Integer dels(List<Integer> ids) {
        return collectMapper.dels(ids);
    }
}
