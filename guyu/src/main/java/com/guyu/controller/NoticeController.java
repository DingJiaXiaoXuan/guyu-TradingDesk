package com.guyu.controller;


import com.guyu.entity.Notice;
import com.guyu.service.NoticeService;
import com.guyu.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    /**
     * 添加公告
     * @param notice
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/AddNotice")
    public int AddNotice(@RequestBody Notice notice){
        return noticeService.AddNotice(notice);
    }

    /**
     * 分页查询（公告）
     * @param page
     * @return
     */
    @RequestMapping("/NoticePage")
    public Page NoticePage(@RequestBody Page page){
        page.setStart();//起始
        page.setPoint();//终止
        page.setCountSun(noticeService.Total());  //总条数
        page.setPageSun();  //总页数
        page.setData(noticeService.QueryNotice(page));//数据
        return page;
    }

    /**
     * 删除公告
     * @param notice
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/DelNotice")
    public int DelNotice(@RequestBody Notice notice){
        return noticeService.DelNotice(notice);
    }

    /**
     * 修改公告
     * @param notice
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/UpdateNotice")
    public int UpdateNotice(@RequestBody Notice notice){
        return noticeService.updateNotice(notice);
    }

    /**
     * 修改公告状态
     * @param notice
     * @return 状态码:1,成功;0,失败;
     */
    @RequestMapping("/NoticeStatus")
    public int NoticeStatus(@RequestBody Notice notice){
        return noticeService.UpdateStatus(notice);
    }

}
