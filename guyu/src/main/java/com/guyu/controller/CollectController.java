package com.guyu.controller;

import com.guyu.entity.Collect;
import com.guyu.service.CollectService;
import com.guyu.utils.CollectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CollectController {

    @Autowired
    CollectService collectService;

    /**
     * 添加收藏
     * @param collect
     * @return 状态码
     */
    @RequestMapping("/AddCollect")
    public int AddCollect(@RequestBody Collect collect){
        return collectService.AddCollect(collect);
    }

    /**
     * 所有收藏
     * @param collect
     * @return Collec
     */
    @RequestMapping("/QueryCollect")
    private List<Collect> QueryCOllect(@RequestBody Collect collect){
        return collectService.QueryCollect(collect);
    }

    /**
     * 我的收藏的总数量
     * @return Integer
     */
    @RequestMapping("/TotalCollect")
    private Integer TotalCollect(@RequestBody Collect collect){
        return collectService.Total(collect);
    }

    /**
     * 删除收藏
     * @param collect
     * @return  状态码
     */
    @RequestMapping("/DelCollect")
    private int DelCollect(@RequestBody Collect collect){
        return collectService.DelCollect(collect);
    }

    /**
     * 批量删除
     * @param ids
     * @return  状态码
     */
    @PostMapping("/CollectDels")
    public int dels(@RequestBody List<Integer> ids){
        if (!ids.isEmpty()){
            return collectService.dels(ids);
        }else {
            return 0;
        }
    }


}
