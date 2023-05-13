package com.guyu.mapper;

import com.guyu.entity.CateMain;
import com.guyu.entity.Category;
import com.guyu.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CategoryMapper {

    /**
     *添加一级种类信息
     * @param cateMain
     * @return 状态码:1,成功;0,失败;
     */
    int AddCateMain(CateMain cateMain);

    /**
     *添加二级种类信息
     * @param category
     * @return 状态码:1,成功;0,失败;
     */
    int AddCate(Category category);

    /**
     * 分页查询(一级分类)
     * @param start
     * @param point
     * @return  数据列表
     */
    List<CateMain> QueryCateMain(@Param("start") Integer start, @Param("point") Integer point);

    /**
     * 分页查询(二级分类)
     * @param start
     * @param point
     * @return  数据列表
     */
    List<Category> QueryCate(@Param("start") Integer start, @Param("point") Integer point);

    /**
     * 删除一级分类信息
     * @param main_id
     * @return 状态码:1,成功;0,失败;
     */
    int DelCateMain(Integer main_id);

    /**
     * 删除二级分类信息
     * @param cate_id
     * @return 状态码:1,成功;0,失败;
     */
    int DelCate(Integer cate_id);

    /**
     * 修改一级分类信息
     * @param cateMain
     * @return 状态码:1,成功;0,失败;
     */
    int UpdateCateMain(CateMain cateMain);

    /**
     * 修改二级分类信息
     * @param category
     * @return 状态码:1,成功;0,失败;
     */
    int UpdateCate(Category category);

    /**
     * 批量删除一级分类
     * @param ids
     * @return 状态码:1,成功;0,失败;
     */
    Integer CateMainDels(List<Integer> ids);

    /**
     * 批量删除二级分类
     * @param ids
     * @return 状态码:1,成功;0,失败;
     */
    Integer CateDels(List<Integer> ids);

    /**
     * 模糊查询(一级分类)
     * @param cateMain
     * @return 状态码:1,成功;0,失败;
     */
    List<CateMain> CateMainQuery(CateMain cateMain);

    /**
     * 模糊查询(二级分类)
     * @param categoryt
     * @return 状态码:1,成功;0,失败;
     */
    List<Category> FuzzyQuery(Category categoryt);

    /**
     * 查询用户信息总条数(一级分类)
     * @return 总条数
     */
    Integer CateMainTotal();

    /**
     * 查询用户信息总条数(二级分类)
     * @return 总条数
     */
    Integer CateTotal();

    /**
     * 更新时间(一级分类)
     * @param cateMain_id
     */
    void CateMainUpdateTime(Integer cateMain_id);

    /**
     * 更新时间(二级分类)
     * @param cate_id
     */
    void CateUpdateTime(Integer cate_id);

    /**
     * 修改一级分类状态
     * @param main_id
     * @param main_status
     * @return 状态:1.成功;0.失败
     */
    int CateMainUpdateStatus(@Param("main_status") int main_status,@Param("main_id") Integer main_id);

    /**
     * 修改二级分类状态
     * @param cate_id
     * @param cate_status
     * @return 状态:1.成功;0.失败
     */
    int CateUpdateStatus(@Param("cate_status") int cate_status,@Param("cate_id") Integer cate_id);

    /**
     * 种类选择器
     * @return  种类数据
     */
    List<CateMain> CateSelect();

    /**
     * 查询所有主类别
     * @return List<CateMain>
     */
    List<CateMain>findCateMain();

    /**
     * 查询所有种类
     * @return  List<Category>
     */
    List<Category>findCate(Integer main_id);

    /**
     * 查询分类（根据种类编号）
     * @param main_id
     * @return  CateMain
     */
    CateMain inquire_main(Integer main_id);

    /**
     * 查询该种类的分类编号
     * @param cate_id
     * @return main_id
     */
    Integer inquire_main_id(Integer cate_id);

    /**
     * 查询种类（根据种类）
     * @param cate_id
     * @return Category
     */
    Category inquire_cate(Integer cate_id);
}
