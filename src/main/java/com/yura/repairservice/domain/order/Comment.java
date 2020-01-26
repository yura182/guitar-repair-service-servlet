package com.yura.repairservice.domain.order;

import com.yura.repairservice.domain.user.User;

import java.time.LocalDateTime;
import java.util.Objects;

public class Comment {
    private final Integer id;
    private final User client;
    private final Order order;
    private final String text;
    private final LocalDateTime date;

    public Comment(Builder builder) {
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
        Comment comment = (Comment) obj;
        return Objects.equals(id, comment.id) &&
                Objects.equals(client, comment.client) &&
                Objects.equals(order, comment.order) &&
                Objects.equals(text, comment.text) &&
                Objects.equals(date, comment.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, order, text, date);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("Comment - ");
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

        public Comment build() {
            return new Comment(this);
        }
    }

}
