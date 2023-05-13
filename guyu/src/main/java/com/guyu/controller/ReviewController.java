package com.guyu.controller;

import com.guyu.entity.Review;
import com.guyu.entity.Store;
import com.guyu.service.ReviewService;
import com.guyu.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    /**
     *添加评论信息
     * @param review
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/ReviewAdd")
    public int ReviewAdd(@RequestBody Review review){
        return reviewService.ReviewAdd(review);
    }

    /**
     * 分页查询
     * @param page
     * @return  数据列表
     */
    @RequestMapping("/ReviewPage")
    public Page ReviewPage(@RequestBody Page page){
        page.setStart();//起始
        page.setPoint();//终止
        page.setCountSun(reviewService.Total());  //总条数
        page.setPageSun();  //总页数
        page.setData(reviewService.ReviewPage(page));//数据
        return page;
    }

    /**
     * 删除评论信息
     * @param review
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/ReviewDel")
    public int ReviewDel(@RequestBody Review review){
        return reviewService.ReviewDel(review);
    }

    /**
     * 修改评论
     * @param review
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/ReviewUpdateContent")
    public int ReviewUpdate(@RequestBody Review review){
        return reviewService.ReviewUpdata(review);
    }

    /**
     * 批量删除
     * @param ids
     * @return  状态码
     */
    @PostMapping("/ReviewDels")
    public int dels(@RequestBody List<Integer> ids){
        if (!ids.isEmpty()){
            return reviewService.ReviewDels(ids);
        }else {
            return 0;
        }
    }

    /**
     * 修改评论状态信息
     * @param review
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/ReviewStatus")
    public int ReviewStatus(@RequestBody Review review){
        return reviewService.UpdateStatus(review);
    }

    /**
     * 商品评论展示(前台)
     * @param review
     * @return List<Review>
     */
    @RequestMapping("/FindReview")
    public List<Review> FindReview(@RequestBody Review review){
        return reviewService.findReview(review);
    }
}
