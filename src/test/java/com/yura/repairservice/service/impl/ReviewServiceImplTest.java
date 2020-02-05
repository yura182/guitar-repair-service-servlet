package com.yura.repairservice.service.impl;

import com.yura.repairservice.domain.order.Order;
import com.yura.repairservice.domain.order.Review;
import com.yura.repairservice.domain.user.User;
import com.yura.repairservice.entity.OrderEntity;
import com.yura.repairservice.entity.ReviewEntity;
import com.yura.repairservice.entity.UserEntity;
import com.yura.repairservice.repository.ReviewRepository;
import com.yura.repairservice.service.ReviewService;
import com.yura.repairservice.service.mapper.EntityMapper;
import com.yura.repairservice.service.validator.Validator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ReviewServiceImplTest {
    private static final Review REVIEW = getReview();
    private static final ReviewEntity REVIEW_ENTITY = getReviewEntity();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private EntityMapper<ReviewEntity, Review> reviewMapper;

    @Mock
    private Validator<Review> reviewValidator;

    @InjectMocks
    private ReviewService reviewService;

    @Test


    private static Review getReview() {
        return Review.builder()
                .withClient(User.builder().withName("Yura").build())
                .withOrder(Order.builder().withService("Service").build())
                .withDate(LocalDateTime.of(1990,12,12,12,12))
                .withText("Text")
                .build();
    }

    private static ReviewEntity getReviewEntity() {
        return ReviewEntity.builder()
                .withClient(UserEntity.builder().withId(1).withName("Yura").build())
                .withOrder(OrderEntity.builder().withId(1).withService("Service").build())
                .withDate(LocalDateTime.of(1990,12,12,12,12))
                .withText("Text")
                .build();
    }
}