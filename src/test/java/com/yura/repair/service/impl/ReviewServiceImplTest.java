package com.yura.repair.service.impl;

import com.yura.repair.dto.OrderDto;
import com.yura.repair.dto.ReviewDto;
import com.yura.repair.dto.UserDto;
import com.yura.repair.entity.OrderEntity;
import com.yura.repair.entity.ReviewEntity;
import com.yura.repair.entity.UserEntity;
import com.yura.repair.exception.EntityNotFoundException;
import com.yura.repair.exception.InvalidParameterException;
import com.yura.repair.repository.ReviewRepository;
import com.yura.repair.service.mapper.EntityMapper;
import com.yura.repair.service.validator.Validator;
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
    private static final ReviewDto REVIEW_DTO = getReviewDto();
    private static final ReviewEntity REVIEW_ENTITY = getReviewEntity();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private EntityMapper<ReviewEntity, ReviewDto> reviewMapper;

    @Mock
    private Validator<ReviewDto> reviewValidator;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    @Test
    public void addShouldAddReview() {
        when(reviewMapper.mapDtoToEntity(REVIEW_DTO)).thenReturn(REVIEW_ENTITY);
        when(reviewRepository.save(REVIEW_ENTITY)).thenReturn(true);

        reviewService.add(REVIEW_DTO);

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
        when(reviewMapper.mapEntityToDto(REVIEW_ENTITY)).thenReturn(REVIEW_DTO);

        ReviewDto actual = reviewService.findById(1);

        assertEquals(REVIEW_DTO, actual);
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
        List<ReviewDto> expected = Collections.singletonList(REVIEW_DTO);
        List<ReviewEntity> reviewEntities = Collections.singletonList(REVIEW_ENTITY);

        when(reviewRepository.findAll(anyInt(), anyInt())).thenReturn(reviewEntities);
        when(reviewMapper.mapEntityToDto(REVIEW_ENTITY)).thenReturn(REVIEW_DTO);

        List<ReviewDto> actual = reviewService.findAll(1, 5);

        assertEquals(expected, actual);
    }

    @Test
    public void findAllShouldReturnEmptyList() {
        List<ReviewDto> expected = Collections.emptyList();
        List<ReviewEntity> reviewEntities = Collections.emptyList();

        when(reviewRepository.findAll(anyInt(), anyInt())).thenReturn(reviewEntities);

        List<ReviewDto> actual = reviewService.findAll(1, 5);

        assertEquals(expected, actual);
    }

    @Test
    public void findAllByOrderShouldReturnListOfReviews() {
        List<ReviewDto> expected = Collections.singletonList(REVIEW_DTO);
        List<ReviewEntity> reviewEntities = Collections.singletonList(REVIEW_ENTITY);

        when(reviewRepository.findAllByOrder(anyInt(), anyInt(), anyInt())).thenReturn(reviewEntities);
        when(reviewMapper.mapEntityToDto(REVIEW_ENTITY)).thenReturn(REVIEW_DTO);

        List<ReviewDto> actual = reviewService.findAllByOrder(1, 1, 5);

        assertEquals(expected, actual);
    }

    @Test
    public void findAllByOrderShouldReturnEmptyList() {
        List<ReviewDto> expected = Collections.emptyList();
        List<ReviewEntity> reviewEntities = Collections.emptyList();

        when(reviewRepository.findAllByOrder(anyInt(), anyInt(), anyInt())).thenReturn(reviewEntities);

        List<ReviewDto> actual = reviewService.findAllByOrder(1, 1, 5);

        assertEquals(expected, actual);
    }

    @Test
    public void findAllByClientShouldReturnListOfReviews() {
        List<ReviewDto> expected = Collections.singletonList(REVIEW_DTO);
        List<ReviewEntity> reviewEntities = Collections.singletonList(REVIEW_ENTITY);

        when(reviewRepository.findAllByOrder(anyInt(), anyInt(), anyInt())).thenReturn(reviewEntities);
        when(reviewMapper.mapEntityToDto(REVIEW_ENTITY)).thenReturn(REVIEW_DTO);

        List<ReviewDto> actual = reviewService.findAllByOrder(1, 1, 5);

        assertEquals(expected, actual);
    }

    @Test
    public void findAllByClientShouldReturnEmptyList() {
        List<ReviewDto> expected = Collections.emptyList();
        List<ReviewEntity> reviewEntities = Collections.emptyList();

        when(reviewRepository.findAllByClient(anyInt(), anyInt(), anyInt())).thenReturn(reviewEntities);

        List<ReviewDto> actual = reviewService.findAllByClient(1, 1, 5);

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


    private static ReviewDto getReviewDto() {
        return ReviewDto.builder()
                .withClient(UserDto.builder().withName("Yura").build())
                .withOrder(OrderDto.builder().withService("Service").build())
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