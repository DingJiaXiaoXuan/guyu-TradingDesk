package com.guyu.service.impl;

import com.guyu.entity.Notice;
import com.guyu.mapper.NoticeMapper;
import com.guyu.service.NoticeService;
import com.guyu.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl  implements NoticeService {

    @Autowired
    NoticeMapper noticeMapper;

    @Override
    public int AddNotice(Notice notice) {
        return noticeMapper.AddNotice(notice);
    }

    @Override
    public List<Notice> QueryNotice(Page page) {
        return noticeMapper.QueryNotice(page.getStart(),page.getPoint());
    }

    @Override
    public int DelNotice(Notice notice) {
        return noticeMapper.DelNotice(notice.getNotice_id());
    }

    @Override
    public int updateNotice(Notice notice) {
        noticeMapper.UpdateTime(notice.getNotice_id());
        return noticeMapper.updateNotice(notice);
    }

    @Override
    public int UpdateStatus(Notice notice) {
        noticeMapper.UpdateTime(notice.getNotice_id());
        if(notice.getNotice_status()==1){
           noticeMapper.publish_time(notice.getNotice_id());
        }
        return noticeMapper.UpdateStatus(notice.getNotice_status(),notice.getNotice_id());

    }


    @Override
    public Integer Total() {
        return noticeMapper.Total();
    }
}
