package com.guyu.mapper;

import com.guyu.entity.Size;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SizeMapper {

    /**
     *添加规格信息
     * @param size
     * @return 状态码:1,成功;0,失败;
     */
    int AddSize(Size size);

    /**
     * 商品规格查询(前端)
     * @param pro_id
     * @return  数据列表
     */
    List<Size> QuerySize(Integer pro_id);

    /**
     * 商品规格查询(后端)
     * @param pro_id
     * @return  数据列表
     */
    List<Size> QuerySizeAfter(Integer pro_id);

    /**
     * 删除规格信息
     * @param size_id
     * @return 状态码:1,成功;0,失败;
     */
    int DelSize(Integer size_id);

    /**
     * 修改规格信息
     * @param size
     * @return 状态码:1,成功;0,失败;
     */
    int UpdateSize(Size size);

    /**
     * 更新时间
     * @param size_id
     */
    void UpdateTime(Integer size_id);

    /**
     * 修改规格状态
     * @param size_status
     * @param size_id
     * @return 状态:1.成功;0.失败
     */
    int UpdateStatus(@Param("size_status") int size_status,@Param("size_id") Integer size_id);
}
