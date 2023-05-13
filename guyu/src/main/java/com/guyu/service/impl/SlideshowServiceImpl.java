package com.guyu.service.impl;

import com.guyu.entity.Slideshow;
import com.guyu.mapper.SlideshowMapper;
import com.guyu.service.SlideshowService;
import com.guyu.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlideshowServiceImpl implements SlideshowService {

    @Autowired
    SlideshowMapper slideshowMapper;

    @Override
    public int AddSlideshow(Slideshow slideshow) {
        return slideshowMapper.AddSlideshow(slideshow);
    }

    @Override
    public List<Slideshow> QuerySlideshow(Page page) {
        return slideshowMapper.QuerySlideshow(page.getStart(),page.getPoint());
    }

    @Override
    public int DelSlideshow(Slideshow slideshow) {
        return slideshowMapper.DelSlideshow(slideshow.getSlide_id());
    }

    @Override
    public int UpdateSlideshowURl(Slideshow slideshow) {
        slideshowMapper.UpdateTime(slideshow.getSlide_id());
        return slideshowMapper.UpdateSlideshowURl(slideshow);
    }

    @Override
    public Integer dels(List<Integer> ids) {
        return slideshowMapper.dels(ids);
    }

    @Override
    public Integer Total() {
        return slideshowMapper.Total();
    }

    @Override
    public int UpdateStatus(Slideshow slideshow) {
        slideshowMapper.UpdateTime(slideshow.getSlide_id());
        return slideshowMapper.UpdateStatus(slideshow.getSlide_status(),slideshow.getSlide_id());
    }

    @Override
    public Slideshow findSlideshow(Integer slide_status) {
        return slideshowMapper.findSlideshow(slide_status);
    }
}
