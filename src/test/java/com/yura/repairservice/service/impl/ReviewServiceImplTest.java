package com.yura.repairservice.service.impl;

import com.yura.repairservice.domain.order.Order;
import com.yura.repairservice.domain.order.Review;
import com.yura.repairservice.domain.user.User;
import com.yura.repairservice.entity.OrderEntity;
import com.yura.repairservice.entity.ReviewEntity;
import com.yura.repairservice.entity.UserEntity;
import com.yura.repairservice.exception.EntityNotFoundException;
import com.yura.repairservice.exception.InvalidParameterException;
import com.yura.repairservice.repository.ReviewRepository;
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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

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
    private ReviewServiceImpl reviewService;

    @Test
    public void addShouldAddReview() {
        when(reviewMapper.mapDomainToEntity(REVIEW)).thenReturn(REVIEW_ENTITY);
        when(reviewRepository.save(REVIEW_ENTITY)).thenReturn(true);

        reviewService.add(REVIEW);

        verify(reviewRepository).save(REVIEW_ENTITY);
    }

    @Test
    public void addShouldThrowInvalidParameterExceptionForNull() {
        exception.expect(InvalidParameterException.class);

        doThrow(InvalidParameterException.class).when(reviewValidator).validate(null);

        reviewService.add(null);
    }

    @Test
    public void findByIdShouldReturnReview() {
        when(reviewRepository.findById(anyInt())).thenReturn(Optional.of(REVIEW_ENTITY));
        when(reviewMapper.mapEntityToDomain(REVIEW_ENTITY)).thenReturn(REVIEW);

        Review actual = reviewService.findById(1);

        assertEquals(REVIEW, actual);
    }

    @Test
    public void findByIdShouldThrowEntityNotFoundException() {
        exception.expect(EntityNotFoundException.class);
        exception.expectMessage("Review not found with provided id");

        when(reviewRepository.findById(anyInt())).thenReturn(Optional.empty());

        reviewService.findById(1);
    }

    @Test
    public void deleteShouldDeleteReview() {
        when(reviewRepository.deleteById(anyInt())).thenReturn(true);
        reviewService.delete(1);

        verify(reviewRepository).deleteById(1);
    }

    @Test
    public void findAllShouldReturnListOfReviews() {
        List<Review> expected = Collections.singletonList(REVIEW);
        List<ReviewEntity> reviewEntities = Collections.singletonList(REVIEW_ENTITY);

        when(reviewRepository.findAll(anyInt(), anyInt())).thenReturn(reviewEntities);
        when(reviewMapper.mapEntityToDomain(REVIEW_ENTITY)).thenReturn(REVIEW);

        List<Review> actual = reviewService.findAll(1, 5);

        assertEquals(expected, actual);
    }

    @Test
    public void findAllShouldReturnEmptyList() {
        List<Review> expected = Collections.emptyList();
        List<ReviewEntity> reviewEntities = Collections.emptyList();

        when(reviewRepository.findAll(anyInt(), anyInt())).thenReturn(reviewEntities);

        List<Review> actual = reviewService.findAll(1, 5);

        assertEquals(expected, actual);
    }

    @Test
    public void findAllByOrderShouldReturnListOfReviews() {
        List<Review> expected = Collections.singletonList(REVIEW);
        List<ReviewEntity> reviewEntities = Collections.singletonList(REVIEW_ENTITY);

        when(reviewRepository.findAllByOrder(anyInt(), anyInt(), anyInt())).thenReturn(reviewEntities);
        when(reviewMapper.mapEntityToDomain(REVIEW_ENTITY)).thenReturn(REVIEW);

        List<Review> actual = reviewService.findAllByOrder(1, 1, 5);

        assertEquals(expected, actual);
    }

    @Test
    public void findAllByOrderShouldReturnEmptyList() {
        List<Review> expected = Collections.emptyList();
        List<ReviewEntity> reviewEntities = Collections.emptyList();

        when(reviewRepository.findAllByOrder(anyInt(), anyInt(), anyInt())).thenReturn(reviewEntities);

        List<Review> actual = reviewService.findAllByOrder(1, 1, 5);

        assertEquals(expected, actual);
    }

    @Test
    public void findAllByClientShouldReturnListOfReviews() {
        List<Review> expected = Collections.singletonList(REVIEW);
        List<ReviewEntity> reviewEntities = Collections.singletonList(REVIEW_ENTITY);

        when(reviewRepository.findAllByOrder(anyInt(), anyInt(), anyInt())).thenReturn(reviewEntities);
        when(reviewMapper.mapEntityToDomain(REVIEW_ENTITY)).thenReturn(REVIEW);

        List<Review> actual = reviewService.findAllByOrder(1, 1, 5);

        assertEquals(expected, actual);
    }

    @Test
    public void findAllByClientShouldReturnEmptyList() {
        List<Review> expected = Collections.emptyList();
        List<ReviewEntity> reviewEntities = Collections.emptyList();

        when(reviewRepository.findAllByClient(anyInt(), anyInt(), anyInt())).thenReturn(reviewEntities);

        List<Review> actual = reviewService.findAllByClient(1, 1, 5);

        assertEquals(expected, actual);
    }

    @Test
    public void numberOfEntriesShouldReturnNumberOfEntries() {
        when(reviewRepository.countAll()).thenReturn(10);

        int expected = 10;
        int actual = reviewService.numberOfEntries();

        assertEquals(expected, actual);
    }

    @Test
    public void numberOfEntriesByOrderIdShouldReturnNumberOfEntries() {
        when(reviewRepository.countByOrderId(anyInt())).thenReturn(10);

        int expected = 10;
        int actual = reviewService.numberOfEntriesByOrderId(1);

        assertEquals(expected, actual);
    }

    @Test
    public void numberOfEntriesByClientIdShouldReturnNumberOfEntries() {
        when(reviewRepository.countByClientId(anyInt())).thenReturn(10);

        int expected = 10;
        int actual = reviewService.numberOfEntriesByClientId(1);

        assertEquals(expected, actual);
    }


    private static Review getReview() {
        return Review.builder()
                .withClient(User.builder().withName("Yura").build())
                .withOrder(Order.builder().withService("Service").build())
                .withDate(LocalDateTime.of(1990, 12, 12, 12, 12))
                .withText("Text")
                .build();
    }

    private static ReviewEntity getReviewEntity() {
        return ReviewEntity.builder()
                .withClient(UserEntity.builder().withId(1).withName("Yura").build())
                .withOrder(OrderEntity.builder().withId(1).withService("Service").build())
                .withDate(LocalDateTime.of(1990, 12, 12, 12, 12))
                .withText("Text")
                .build();
    }
}