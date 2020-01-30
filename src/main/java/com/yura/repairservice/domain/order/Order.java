package com.yura.repairservice.domain.order;

import com.yura.repairservice.domain.instrument.Instrument;
import com.yura.repairservice.domain.user.User;

import java.time.LocalDateTime;
import java.util.Objects;

public class Order {
    private final Integer id;
    private final User client;
    private final User master;
    private final Instrument instrument;
    private final LocalDateTime dateTime;
    private final String service;
    private final Double price;
    private final Status status;
    private final String rejectionReason;

    public Order(Builder builder) {
        this.id = builder.id;
        this.client = builder.client;
        this.master = builder.master;
        this.instrument = builder.instrument;
        this.dateTime = builder.dateTime;
        this.service = builder.service;
        this.price = builder.price;
        this.status = builder.status;
        this.rejectionReason = builder.rejectionReason;
    }

    public Order(Order order, Status status, Double price) {
        this.id = order.id;
        this.client = order.client;
        this.master = order.master;
        this.instrument = order.instrument;
        this.dateTime = order.dateTime;
        this.service = order.service;
        this.price = price;
        this.status = status;
        this.rejectionReason = order.rejectionReason;
    }

    public Order(Order order, Status status) {
        this.id = order.id;
        this.client = order.client;
        this.master = order.master;
        this.instrument = order.instrument;
        this.dateTime = order.dateTime;
        this.service = order.service;
        this.price = order.price;
        this.status = status;
        this.rejectionReason = order.rejectionReason;
    }


    public Order(Order order, Status status, String rejectionReason) {
        this.id = order.id;
        this.client = order.client;
        this.master = order.master;
        this.instrument = order.instrument;
        this.dateTime = order.dateTime;
        this.service = order.service;
        this.price = order.price;
        this.status = status;
        this.rejectionReason = rejectionReason;
    }

    public Order(Order order, Status status, User master) {
        this.id = order.id;
        this.client = order.client;
        this.master = master;
        this.instrument = order.instrument;
        this.dateTime = order.dateTime;
        this.service = order.service;
        this.price = order.price;
        this.status = status;
        this.rejectionReason = order.rejectionReason;
    }

    public Order(Order order, Double price) {
        this.id = order.id;
        this.client = order.client;
        this.master = order.master;
        this.instrument = order.instrument;
        this.dateTime = order.dateTime;
        this.service = order.service;
        this.price = price;
        this.status = order.status;
        this.rejectionReason = order.rejectionReason;
    }

    public Integer getId() {
        return id;
    }

    public User getClient() {
        return client;
    }

    public User getMaster() {
        return master;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getService() {
        return service;
    }

    public Double getPrice() {
        return price;
    }

    public Status getStatus() {
        return status;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Order order = (Order) obj;
        return Objects.equals(id, order.id) &&
                Objects.equals(client, order.client) &&
                Objects.equals(master, order.master) &&
                Objects.equals(instrument, order.instrument) &&
                Objects.equals(dateTime, order.dateTime) &&
                Objects.equals(service, order.service) &&
                Objects.equals(price, order.price) &&
                status == order.status &&
                Objects.equals(rejectionReason, order.rejectionReason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, master, instrument, dateTime, service, price, status, rejectionReason);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("Order - ");
        result.append("id: ").append(id).append(", ");
        result.append("client: ").append(client).append(", ");
        result.append("master: ").append(master).append(", ");
        result.append("instrument: ").append(instrument).append(", ");
        result.append("dateTime: ").append(dateTime).append(", ");
        result.append("service: ").append(service).append(", ");
        result.append("price: ").append(price).append(", ");
        result.append("status: ").append(status).append(", ");
        result.append("rejectionReason: ").append(rejectionReason);

        return result.toString();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer id;
        private User client;
        private User master;
        private Instrument instrument;
        private LocalDateTime dateTime;
        private String service;
        private Double price;
        private Status status;
        private String rejectionReason;

        private Builder() {
        }

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withUser(User user) {
            this.client = user;
            return this;
        }

        public Builder withMaster(User user) {
            this.master = user;
            return this;
        }

        public Builder withInstrument(Instrument instrument) {
            this.instrument = instrument;
            return this;
        }

        public Builder withDate(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public Builder withService(String service) {
            this.service = service;
            return this;
        }

        public Builder withPrice(Double price) {
            this.price = price;
            return this;
        }

        public Builder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder withRejectionReason(String rejectionReason) {
            this.rejectionReason = rejectionReason;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
