package com.yura.repair.dto;

import com.yura.repair.entity.Status;

import java.time.LocalDateTime;
import java.util.Objects;

public class OrderDto {
    private final Integer id;
    private final UserDto client;
    private final UserDto master;
    private final InstrumentDto instrumentDto;
    private final LocalDateTime dateTime;
    private final String service;
    private final Double price;
    private final Status status;
    private final String rejectionReason;

    public OrderDto(Builder builder) {
        this.id = builder.id;
        this.client = builder.client;
        this.master = builder.master;
        this.instrumentDto = builder.instrumentDto;
        this.dateTime = builder.dateTime;
        this.service = builder.service;
        this.price = builder.price;
        this.status = builder.status;
        this.rejectionReason = builder.rejectionReason;
    }

    public OrderDto(OrderDto orderDto, Status status, Double price) {
        this.id = orderDto.id;
        this.client = orderDto.client;
        this.master = orderDto.master;
        this.instrumentDto = orderDto.instrumentDto;
        this.dateTime = orderDto.dateTime;
        this.service = orderDto.service;
        this.price = price;
        this.status = status;
        this.rejectionReason = orderDto.rejectionReason;
    }

    public OrderDto(OrderDto orderDto, Status status) {
        this.id = orderDto.id;
        this.client = orderDto.client;
        this.master = orderDto.master;
        this.instrumentDto = orderDto.instrumentDto;
        this.dateTime = orderDto.dateTime;
        this.service = orderDto.service;
        this.price = orderDto.price;
        this.status = status;
        this.rejectionReason = orderDto.rejectionReason;
    }


    public OrderDto(OrderDto orderDto, Status status, String rejectionReason) {
        this.id = orderDto.id;
        this.client = orderDto.client;
        this.master = orderDto.master;
        this.instrumentDto = orderDto.instrumentDto;
        this.dateTime = orderDto.dateTime;
        this.service = orderDto.service;
        this.price = orderDto.price;
        this.status = status;
        this.rejectionReason = rejectionReason;
    }

    public OrderDto(OrderDto orderDto, Status status, UserDto master) {
        this.id = orderDto.id;
        this.client = orderDto.client;
        this.master = master;
        this.instrumentDto = orderDto.instrumentDto;
        this.dateTime = orderDto.dateTime;
        this.service = orderDto.service;
        this.price = orderDto.price;
        this.status = status;
        this.rejectionReason = orderDto.rejectionReason;
    }

    public OrderDto(OrderDto orderDto, Double price) {
        this.id = orderDto.id;
        this.client = orderDto.client;
        this.master = orderDto.master;
        this.instrumentDto = orderDto.instrumentDto;
        this.dateTime = orderDto.dateTime;
        this.service = orderDto.service;
        this.price = price;
        this.status = orderDto.status;
        this.rejectionReason = orderDto.rejectionReason;
    }

    public Integer getId() {
        return id;
    }

    public UserDto getClient() {
        return client;
    }

    public UserDto getMaster() {
        return master;
    }

    public InstrumentDto getInstrument() {
        return instrumentDto;
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
        OrderDto orderDto = (OrderDto) obj;
        return Objects.equals(id, orderDto.id) &&
                Objects.equals(client, orderDto.client) &&
                Objects.equals(master, orderDto.master) &&
                Objects.equals(instrumentDto, orderDto.instrumentDto) &&
                Objects.equals(dateTime, orderDto.dateTime) &&
                Objects.equals(service, orderDto.service) &&
                Objects.equals(price, orderDto.price) &&
                status == orderDto.status &&
                Objects.equals(rejectionReason, orderDto.rejectionReason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, master, instrumentDto, dateTime, service, price, status, rejectionReason);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("Order - ");
        result.append("id: ").append(id).append(", ");
        result.append("client: ").append(client).append(", ");
        result.append("master: ").append(master).append(", ");
        result.append("instrument: ").append(instrumentDto).append(", ");
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
        private UserDto client;
        private UserDto master;
        private InstrumentDto instrumentDto;
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

        public Builder withUser(UserDto userDto) {
            this.client = userDto;
            return this;
        }

        public Builder withMaster(UserDto userDto) {
            this.master = userDto;
            return this;
        }

        public Builder withInstrument(InstrumentDto instrumentDto) {
            this.instrumentDto = instrumentDto;
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

        public OrderDto build() {
            return new OrderDto(this);
        }
    }
}
