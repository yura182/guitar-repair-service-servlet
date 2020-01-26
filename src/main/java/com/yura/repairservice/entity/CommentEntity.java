package com.yura.repairservice.entity;

import com.yura.repairservice.domain.order.Comment;

import java.time.LocalDateTime;
import java.util.Objects;

public class CommentEntity {
    private final Integer id;
    private final UserEntity client;
    private final OrderEntity order;
    private final String text;
    private final LocalDateTime date;

    public CommentEntity(Builder builder) {
        this.id = builder.id;
        this.client = builder.client;
        this.order = builder.order;
        this.text = builder.text;
        this.date = builder.date;
    }

    public Integer getId() {
        return id;
    }

    public UserEntity getClient() {
        return client;
    }

    public OrderEntity getOrder() {
        return order;
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
        CommentEntity that = (CommentEntity) obj;
        return Objects.equals(id, that.id) &&
                Objects.equals(client, that.client) &&
                Objects.equals(order, that.order) &&
                Objects.equals(text, that.text) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, order, text, date);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("CommentEntity - ");
        result.append("id: ").append(id).append(", ");
        result.append("client: ").append(client).append(", ");
        result.append("order: ").append(order).append(", ");
        result.append("date: ").append(date).append(", ");
        result.append("text: ").append(text);

        return result.toString();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer id;
        private UserEntity client;
        private OrderEntity order;
        private String text;
        private LocalDateTime date;

        private Builder() {
        }

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withClient(UserEntity user) {
            this.client = user;
            return this;
        }

        public Builder withOrder(OrderEntity order) {
            this.order = order;
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

        public CommentEntity build() {
            return new CommentEntity(this);
        }
    }
}
