package com.guyu.mapper;

import com.guyu.entity.Market;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MarketMapper {

    /**
     * 添加行情信息
     * @param market
     * @return  状态码:1,成功;0,失败;
     */
    int AddMarket(Market market);

    /**
     * 分页查询
     * @param start
     * @param point
     * @return  List<Market>
     */
    List<Market> QueryMarket(@Param("start") Integer start, @Param("point") Integer point);

    /**
     * 删除行情
     * @param market_id
     * @return 状态码:1,成功;0,失败;
     */
    int DelMarket(Integer market_id);

    /**
     * 修改行情
     * @param market
     * @return   状态码:1,成功;0,失败;
     */
    int UpdateMarket(Market market);

    /**
     * 批量删除
     * @param ids
     * @return 状态码:1,成功;0,失败;
     */
    Integer dels(List<Integer> ids);

    /**
     * 修改状态
     * @param market_status
     * @param market_id
     * @return 状态码:1,成功;0,失败;
     */
    int UpdateStatus(@Param("market_status") int market_status,@Param("market_id") Integer market_id);
}
