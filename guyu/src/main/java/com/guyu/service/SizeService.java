package com.guyu.service;

import com.guyu.entity.Size;
import com.guyu.entity.Store;
import com.guyu.utils.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SizeService {

    /**
     *添加规格信息
     * @param size
     * @return 状态码:1,成功;0,失败;
     */
    int SizeAdd(Size size);

    /**
     * 商品规格查询(前端)
     * @param size
     * @return  数据列表
     */
    List<Size> FindSize(Size size);

    /**
     * 商品规格查询(后端)
     * @param size
     * @return  数据列表
     */
    List<Size> FindSizeAfter(Size size);

    /**
     * 删除规格信息
     * @param size
     * @return 状态码:1,成功;0,失败;
     */
    int SizeDel(Size size);

    /**
     * 修改规格信息
     * @param size
     * @return 状态码:1,成功;0,失败;
     */
    int SizeUpdata(Size size);

    /**
     * 修改状态
     * @param size
     */
    int UpdateStatus(Size size);
}
