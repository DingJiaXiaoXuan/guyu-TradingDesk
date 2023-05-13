package com.guyu.mapper;

import com.guyu.entity.Product;
import com.guyu.entity.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface StoreMapper {

    /**
     *添加店铺信息
     * @param store
     * @return 状态码:1,成功;0,失败;
     */
    int AddStore(Store store);

    /**
     * 分页查询
     * @param start
     * @param point
     * @return  数据列表
     */
    List<Store> QueryStore(@Param("start") Integer start, @Param("point") Integer point);

    /**
     * 查询用户名下店铺
     * @param user_id
     * @return  数据列表
     */
    List<Store> QueryUserStore(Integer user_id);

    /**
     * 分页查询
     * @param start
     * @param point
     * @return  数据列表
     */
    List<Store> QueryStoreAudit(@Param("start") Integer start, @Param("point") Integer point,@Param("store_status") int store_status);

    /**
     * 分页查询（商家）
     * @param start
     * @param point
     * @return  数据列表
     */
    List<Store> QueryStoreUser(@Param("start") Integer start, @Param("point") Integer point,@Param("user_id") int user_id);

    /**
     * 删除店铺信息
     * @param store_id
     * @return 状态码:1,成功;0,失败;
     */
    int DelStore(Integer store_id);

    /**
     * 修改店铺信息
     * @param store
     * @return 状态码:1,成功;0,失败;
     */
    int UpdateStore(Store store);

    /**
     * 批量删除店铺信息
     * @param ids
     * @return 状态码:1,成功;0,失败;
     */
    Integer dels(List<Integer> ids);

    /**
     * 模糊查询
     * @param store
     * @return 状态码:1,成功;0,失败;
     */
    List<Store> FuzzyQuery(Store store);

    /**
     * 查询信息总条数
     * @return 总条数
     */
    Integer Total();

    /**
     * 查询信息总条数
     * @return 总条数
     */
    Integer Total_user(Integer user_id);

    /**
     * 查询该店铺商品数量
     * @param store_id
     * @return
     */
    Integer findPro_num(Integer store_id);

    /**
     * 修改该店铺商品数量
     * @param store_id
     * @return Integer
     */
    Integer updatePro_num(@Param("pro_num") Integer pro_num,@Param("store_id") Integer store_id);

    /**
     * 查询信息总条数(状态查询)
     * @return 总条数
     */
    Integer Total_status(Integer store_status);

    /**
     * 更新时间
     * @param store_id
     */
    void UpdateTime(Integer store_id);

    /**
     * 修改商品状态
     * @param store_id
     * @param store_status
     * @return 状态:1.成功;0.失败
     */
    int UpdateStatus(@Param("store_status") Integer store_status,@Param("store_id") Integer store_id);

    /**
     * 查询店铺信息
     * @param store_id
     * @return Store
     */
    Store findStore(Integer store_id);
}
