package com.guyu.controller;

import com.guyu.entity.Slideshow;
import com.guyu.service.SlideshowService;
import com.guyu.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SlideshowController {

    @Autowired
    SlideshowService slideshowService;

    /**
     *添加轮播图信息
     * @param slideshow
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/SlideshowAdd")
    public int SlideshowAdd(@RequestBody Slideshow slideshow){
        return slideshowService.AddSlideshow(slideshow);
    }

    /**
     * 分页查询
     * @param page
     * @return  数据列表
     */
    @RequestMapping("/SlideshowPage")
    public Page SlideshowPage(@RequestBody Page page){
        page.setStart();//起始
        page.setPoint();//终止
        page.setCountSun(slideshowService.Total());  //总条数
        page.setPageSun();  //总页数
        page.setData(slideshowService.QuerySlideshow(page));//数据
        return page;
    }

    /**
     * 删除轮播图信息
     * @param slideshow
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/SlideshowDel")
    public int SlideshowDel(@RequestBody Slideshow slideshow){
        return slideshowService.DelSlideshow(slideshow);
    }

    /**
     * 修改轮播图URL
     * @param slideshow
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/UpdateSlideshowURl")
    public int UpdateSlideshowURl(@RequestBody Slideshow slideshow){
        return slideshowService.UpdateSlideshowURl(slideshow);
    }

    /**
     * 批量删除
     * @param ids
     * @return  状态码
     */
    @PostMapping("/SlideshowDels")
    public int dels(@RequestBody List<Integer> ids){
        if (!ids.isEmpty()){
            return slideshowService.dels(ids);
        }else {
            return 0;
        }
    }

    /**
     * 修改轮播图状态信息
     * @param slideshow
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/SlideshowStatus")
    public int SlideshowStatus(@RequestBody Slideshow slideshow){
        return slideshowService.UpdateStatus(slideshow);
    }

    /**
     * 查询正在使用的轮播图
     * @return
     */
    @RequestMapping("/SlideshowFind")
    public List<Slideshow> SlideshowFind(){
        List<Slideshow> list = new ArrayList<Slideshow>();
        for(int i=1;i<=4;i++){
            list.add(slideshowService.findSlideshow(i));
        }
        return list;
    }
}
