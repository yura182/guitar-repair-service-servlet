package com.yura.repair.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class ReviewDto {
    private final Integer id;
    private final UserDto client;
    private final OrderDto orderDto;
    private final String text;
    private final LocalDateTime date;

    public ReviewDto(Builder builder) {
        this.id = builder.id;
        this.client = builder.client;
        this.orderDto = builder.orderDto;
        this.text = builder.text;
        this.date = builder.date;
    }

    public Integer getId() {
        return id;
    }

    public UserDto getClient() {
        return client;
    }

    public OrderDto getOrderDto() {
        return orderDto;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ReviewDto reviewDto = (ReviewDto) obj;
        return Objects.equals(id, reviewDto.id) &&
                Objects.equals(client, reviewDto.client) &&
                Objects.equals(orderDto, reviewDto.orderDto) &&
                Objects.equals(text, reviewDto.text) &&
                Objects.equals(date, reviewDto.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, orderDto, text, date);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("ReviewDto - ");
        result.append("id: ").append(id).append(", ");
        result.append("client: ").append(client).append(", ");
        result.append("order: ").append(orderDto).append(", ");
        result.append("date: ").append(date).append(", ");
        result.append("text: ").append(text);

        return result.toString();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer id;
        private UserDto client;
        private OrderDto orderDto;
        private String text;
        private LocalDateTime date;

        private Builder() {
        }

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withClient(UserDto userDto) {
            this.client = userDto;
            return this;
        }

        public Builder withOrder(OrderDto orderDto) {
            this.orderDto = orderDto;
            return this;
        }

        public Builder withText(String text) {
            this.text = text;
            return this;
        }

        public Builder withDate(LocalDateTime date) {
            this.date = date;
            return this;
        }

        public ReviewDto build() {
            return new ReviewDto(this);
        }
    }

}
