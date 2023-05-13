package com.guyu.service.impl;

import com.guyu.entity.Review;
import com.guyu.mapper.ReviewMapper;
import com.guyu.service.ReviewService;
import com.guyu.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewMapper reviewMapper;

    @Override
    public int ReviewAdd(Review review) {
        review.setReview_status(0);
        return reviewMapper.AddReview(review);
    }

    @Override
    public Integer Total() {
        return reviewMapper.Total();
    }

    @Override
    public List<Review> ReviewPage(Page page) {
        return reviewMapper.QueryReview(page.getStart(),page.getPoint());
    }

    @Override
    public int ReviewDel(Review review) {
        return reviewMapper.DelReview(review.getReview_id());
    }

    @Override
    public int ReviewUpdata(Review review) {
        reviewMapper.UpdateTime(review.getReview_id());
        return reviewMapper.UpdateReview(review);
    }

    @Override
    public int ReviewDels(List<Integer> ids) {
        return reviewMapper.dels(ids);
    }

    @Override
    public int UpdateStatus(Review review) {
        reviewMapper.UpdateTime(review.getReview_id());
        return reviewMapper.UpdateStatus(review.getReview_status(),review.getReview_id());
    }

    @Override
    public List<Review> findReview(Review review) {
        return reviewMapper.findReview(review);
    }
}
