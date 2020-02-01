package com.yura.repairservice.domain.order;

import com.yura.repairservice.domain.user.User;

import java.time.LocalDateTime;
import java.util.Objects;

public class Review {
    private final Integer id;
    private final User client;
    private final Order order;
    private final String text;
    private final LocalDateTime date;

    public Review(Builder builder) {
        this.id = builder.id;
        this.client = builder.client;
        this.order = builder.order;
        this.text = builder.text;
        this.date = builder.date;
    }

    public Integer getId() {
        return id;
    }

    public User getClient() {
        return client;
    }

    public Order getOrder() {
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
        Review review = (Review) obj;
        return Objects.equals(id, review.id) &&
                Objects.equals(client, review.client) &&
                Objects.equals(order, review.order) &&
                Objects.equals(text, review.text) &&
                Objects.equals(date, review.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, order, text, date);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("Review - ");
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
        private User client;
        private Order order;
        private String text;
        private LocalDateTime date;

        private Builder() {
        }

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withClient(User user) {
            this.client = user;
            return this;
        }

        public Builder withOrder(Order order) {
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

        public Review build() {
            return new Review(this);
        }
    }

}
