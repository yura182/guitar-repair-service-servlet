package com.yura.repairservice.entity;

import com.yura.repairservice.domain.order.Status;

import java.time.LocalDateTime;
import java.util.Objects;

public class OrderEntity {
    private final Integer id;
    private final UserEntity client;
    private final UserEntity master;
    private final InstrumentEntity instrument;
    private final LocalDateTime dateTime;
    private final String service;
    private final Double price;
    private final Status status;
    private final String rejectionReason;

    public OrderEntity(Builder builder) {
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

    public Integer getId() {
        return id;
    }

    public UserEntity getClient() {
        return client;
    }

    public UserEntity getMaster() {
        return master;
    }

    public InstrumentEntity getInstrument() {
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
        OrderEntity order = (OrderEntity) obj;
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

        result.append("OrderEntity - ");
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
        private UserEntity client;
        private UserEntity master;
        private InstrumentEntity instrument;
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

        public Builder withUser(UserEntity user) {
            this.client = user;
            return this;
        }

        public Builder withMaster(UserEntity user) {
            this.master = user;
            return this;
        }

        public Builder withInstrument(InstrumentEntity instrument) {
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

        public OrderEntity build() {
            return new OrderEntity(this);
        }
    }
}
