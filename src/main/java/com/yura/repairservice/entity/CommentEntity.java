package com.yura.repairservice.entity;

import java.util.Objects;

public class CommentEntity {
    private final Integer id;
    private final UserEntity client;
    private final OrderEntity order;
    private final String text;

    public CommentEntity(Builder builder) {
        this.id = builder.id;
        this.client = builder.client;
        this.order = builder.order;
        this.text = builder.text;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CommentEntity comment = (CommentEntity) obj;
        return Objects.equals(id, comment.id) &&
                Objects.equals(client, comment.client) &&
                Objects.equals(order, comment.order) &&
                Objects.equals(text, comment.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, order, text);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("CommentEntity - ");
        result.append("id: ").append(id).append(", ");
        result.append("client: ").append(client).append(", ");
        result.append("order: ").append(order).append(", ");
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
    }

}
