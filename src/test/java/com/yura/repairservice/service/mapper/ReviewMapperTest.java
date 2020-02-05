package com.yura.repairservice.service.mapper;

import com.yura.repairservice.domain.order.Order;
import com.yura.repairservice.domain.order.Review;
import com.yura.repairservice.domain.user.User;
import com.yura.repairservice.entity.OrderEntity;
import com.yura.repairservice.entity.ReviewEntity;
import com.yura.repairservice.entity.UserEntity;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReviewMapperTest {
    private static final User USER = User.builder().build();
    private static final UserEntity USER_ENTITY = UserEntity.builder().build();
    private static final Order ORDER = Order.builder().build();
    private static final OrderEntity ORDER_ENTITY = OrderEntity.builder().build();
    private static final Review REVIEW = getReview();
    private static final ReviewEntity REVIEW_ENTITY = getReviewEntity();

    private UserMapper userMapper;
    private OrderMapper orderMapper;
    private ReviewMapper reviewMapper;

    @Before
    public void init() {
        userMapper = mock(UserMapper.class);
        orderMapper = mock(OrderMapper.class);

        reviewMapper = new ReviewMapper(userMapper, orderMapper);
    }

    @Test
    public void mapDomainToEntityShouldMapToEntity() {
        when(userMapper.mapDomainToEntity(USER)).thenReturn(USER_ENTITY);
        when(orderMapper.mapDomainToEntity(ORDER)).thenReturn(ORDER_ENTITY);

        ReviewEntity actual = reviewMapper.mapDomainToEntity(REVIEW);

        assertEquals(REVIEW_ENTITY, actual);
    }

    @Test
    public void mapEntityToDomainShouldMapToDomain() {
        when(userMapper.mapEntityToDomain(USER_ENTITY)).thenReturn(USER);
        when(orderMapper.mapEntityToDomain(ORDER_ENTITY)).thenReturn(ORDER);

        Review actual = reviewMapper.mapEntityToDomain(REVIEW_ENTITY);

        assertEquals(REVIEW, actual);
    }

    private static Review getReview() {
        return Review.builder()
                .withId(1)
                .withOrder(ORDER)
                .withClient(USER)
                .withText("Text")
                .withDate(LocalDateTime.of(1990, 12, 12, 12, 12))
                .build();
    }

    private static ReviewEntity getReviewEntity() {
        return ReviewEntity.builder()
                .withId(1)
                .withOrder(ORDER_ENTITY)
                .withClient(USER_ENTITY)
                .withText("Text")
                .withDate(LocalDateTime.of(1990, 12, 12, 12, 12))
                .build();
    }
}