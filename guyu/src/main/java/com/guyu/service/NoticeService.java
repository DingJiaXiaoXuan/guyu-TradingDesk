package com.guyu.service;

import com.guyu.entity.Notice;
import com.guyu.utils.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NoticeService {

    /**
     * 添加公告
     * @param notice
     * @return 状态码:1,成功;0,失败;
     */
    int AddNotice(Notice notice);

    /**
     * 分页查询
     * @param page
     * @return List<Notice>
     */
    List<Notice> QueryNotice(Page page);

    /**
     * 删除店铺信息
     * @param notice
     * @return 状态码:1,成功;0,失败;
     */
    int DelNotice(Notice notice);

    /**
     * 修改公告
     * @param notice
     * @return 状态码:1,成功;0,失败;
     */
    int updateNotice(Notice notice);

    /**
     * 修改公告状态
     * @param notice
     * @return 状态:1.成功;0.失败
     */
    int UpdateStatus(Notice notice);


    /**
     * 查询公告信息总条数
     * @return 总条数
     */
    Integer Total();
}
