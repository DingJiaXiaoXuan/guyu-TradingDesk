package com.guyu.mapper;

import com.guyu.entity.Collect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CollectMapper {

    /**
     * 添加收藏
     * @param collect
     * @return  状态码:1,成功;0,失败;
     */
    int AddCollect(Collect collect);

    /**
     * 查询收藏
     * @param user_id
     * @return  数据列表
     */
    List<Collect> QueryCollect(Integer user_id);

    /**
     * 查询收藏总条数
     * @return 总条数
     */
    Integer Total(Integer user_id);

    /**
     * 删除收藏
     * @param collect_id
     * @return 状态码:1,成功;0,失败;
     */
    int DelCollect(Integer collect_id);

    /**
     * 批量删除收藏
     * @param ids
     * @return 状态码:1,成功;0,失败;
     */
    Integer dels(List<Integer> ids);

    /**
     * 查询是否已经收藏
     * @param pro_id
     * @return  Collect
     */
    Collect FindPro_id(Integer pro_id);
}
