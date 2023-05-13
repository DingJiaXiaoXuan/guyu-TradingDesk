package com.guyu.mapper;

import com.guyu.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface ProductMapper {

    /**
     *添加商品信息
     * @param product
     * @return 状态码:1,成功;0,失败;
     */
    int AddProduct(Product product);

    /**
     * 分页查询
     * @param start
     * @param point
     * @return  数据列表
     */
    List<Product> QueryProduct(@Param("start") Integer start, @Param("point") Integer point);

    /**
     * 分页查询(前端)
     * @param start
     * @param point
     * @return  数据列表
     */
    List<Product> QueryAllProduct(@Param("start") Integer start, @Param("point") Integer point);

    /**
     * 分页查询(商品审核)
     * @param start
     * @param point
     * @param pro_status
     * @return
     */
    List<Product> QueryProductAudit(@Param("start") Integer start, @Param("point") Integer point,@Param("pro_status")Integer pro_status);

    /**
     * 分页查询(所有商品分类查询)
     * @param start
     * @param point
     * @return  数据列表
     */
    List<Product> CateProduct(@Param("start") Integer start, @Param("point") Integer point,@Param("cate_id")Integer cate_id);

    /**
     * 分页查询(所有商品类别查询)
     * @param start
     * @param point
     * @return  数据列表
     */
    List<Product> MainProduct(@Param("start") Integer start, @Param("point") Integer point,@Param("main_id")Integer main_id);

    /**
     * 分页查询(店铺查询)
     * @param start
     * @param point
     * @return  数据列表
     */
    List<Product> StoreProduct(@Param("start") Integer start, @Param("point") Integer point,@Param("store_id")Integer store_id);

    /**
     * 分页查询(用户查询)
     * @param start
     * @param point
     * @param user_id
     * @return  数据列表
     */
    List<Product> UserProduct(@Param("start") Integer start, @Param("point") Integer point,@Param("user_id")Integer user_id);

    /**
     * 推荐商品查询
     * @return  数据列表
     */
    List<Product> RecommendProduct(Integer cate_id);

    /**
     * 删除商品信息
     * @param pro_id
     * @return 状态码:1,成功;0,失败;
     */
    int DelProduct(Integer pro_id);

    /**
     * 删除该店铺所有商品信息
     * @param store_id
     * @return 状态码:1,成功;0,失败;
     */
    int DelProStore(Integer store_id);

    /**
     * 修改商品信息
     * @param product
     * @return 状态码:1,成功;0,失败;
     */
    int UpdateProduct(Product product);

    /**
     * 批量删除商品信息
     * @param ids
     * @return 状态码:1,成功;0,失败;
     */
    Integer dels(List<Integer> ids);

    /**
     * 批量删除商品信息（所属店铺）
     * @param ids
     * @return 状态码:1,成功;0,失败;
     */
    Integer dels_store(List<Integer> ids);

    /**
     * 模糊查询
     * @param product
     * @return 状态码:1,成功;0,失败;
     */
    List<Product> FuzzyQuery(Product product);

    /**
     * 查询用户信息总条数
     * @return 总条数
     */
    Integer Total();

    /**
     * 查询用户信息总条数(同分类)
     * @return 总条数
     */
    Integer Total_cate(Integer cate_id);

    /**
     * 查询用户信息总条数（状态查询）
     * @return 总条数
     */
    Integer Total_status(Integer pro_status);

    /**
     * 查询用户信息总条数（状态查询）
     * @return 总条数
     */
    Integer Total_store(Integer store_id);

    /**
     * 查询用户信息总条数（状态查询）
     * @return 总条数
     */
    Integer Total_user(Integer user_id);

    /**
     * 查询商品信息总条数（状态查询）
     * @return 总条数
     */
    Integer Total_AllPro();

    /**
     * 根据商品编号，查询所属店铺编号
     * @param pro_id
     * @return Integer
     */
    Integer findStore_id(Integer pro_id);

    /**
     * 更新时间
     * @param pro_id
     */
    void UpdateTime(Integer pro_id);

    /**
     * 修改商品状态
     * @param pro_id
     * @param pro_status
     * @return 状态:1.成功;0.失败
     */
    int UpdateStatus(@Param("pro_status") int pro_status,@Param("pro_id") Integer pro_id);

    /**
     * 查找商品信息
     * @param pro_id
     * @return Product
     */
    Product findProduct(Integer pro_id);

    /**
     * 下架店铺所有商品
     * @param store_id
     * @return 状态:1.成功;0.失败
     */
    int updateStorePro(Integer store_id);

}
