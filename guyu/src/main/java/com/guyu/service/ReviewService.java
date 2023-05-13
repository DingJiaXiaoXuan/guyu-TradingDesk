package com.guyu.service;

import com.guyu.entity.Review;
import com.guyu.utils.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {


    int ReviewAdd(Review review);

    Integer Total();

    List<Review> ReviewPage(Page page);

    int ReviewDel(Review review);

    int ReviewUpdata(Review review);

    int ReviewDels(List<Integer> ids);

    int UpdateStatus(Review review);

    List<Review> findReview(Review review);
}
