package com.guyu.controller;

import com.guyu.entity.Size;
import com.guyu.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SizeController {

    @Autowired
    SizeService sizeService;

    /**
     *添加规格信息
     * @param size
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/AddSize")
    public int AddSize(@RequestBody Size size){
        return sizeService.SizeAdd(size);
    }

    /**
     * 商品规格查询(前端)
     * @param size
     * @return  数据列表
     */
    @RequestMapping("/FindSize")
    public List<Size> FindSize(@RequestBody Size size){
        return sizeService.FindSize(size);
    }

    /**
     * 商品规格查询(后端)
     * @param size
     * @return  数据列表
     */
    @RequestMapping("/FindSizeAfter")
    public List<Size> FindSizeAfter(@RequestBody Size size){
        return sizeService.FindSizeAfter(size);
    }

    /**
     * 删除规格信息
     * @param size
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/delSize")
    public int delSize(@RequestBody Size size){
        return sizeService.SizeDel(size);
    }

    /**
     * 修改规格信息
     * @param size
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/UpdateSize")
    public int UpdataSize(@RequestBody Size size){
        System.out.println(size.toString());
        return sizeService.SizeUpdata(size);
    }

    /**
     * 修改状态
     * @param size
     */
    @RequestMapping("/SizeStatus")
    public int SizeStatus(@RequestBody Size size){
        return sizeService.UpdateStatus(size);
    }
}
