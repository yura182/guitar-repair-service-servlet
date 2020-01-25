package com.yura.repairservice.entity;

import java.util.Objects;

public class InstrumentEntity {
    private final Integer id;
    private final String brand;
    private final String model;
    private final Integer manufactureYear;

    public InstrumentEntity(Builder builder) {
        this.id = builder.id;
        this.brand = builder.brand;
        this.model = builder.model;
        this.manufactureYear = builder.manufactureYear;
    }

    public Integer getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Integer getManufactureYear() {
        return manufactureYear;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        InstrumentEntity that = (InstrumentEntity) obj;
        return Objects.equals(id, that.id) &&
                Objects.equals(brand, that.brand) &&
                Objects.equals(model, that.model) &&
                Objects.equals(manufactureYear, that.manufactureYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, manufactureYear);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("InstrumentEntity - ");
        result.append("id: ").append(id).append(", ");
        result.append("brand: ").append(brand).append(", ");
        result.append("model: ").append(model).append(", ");
        result.append("manufacture year: ").append(manufactureYear).append(", ");

        return result.toString();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer id;
        private String brand;
        private String model;
        private Integer manufactureYear;

        private Builder() {
        }

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder withModel(String model) {
            this.model = model;
            return this;
        }

        public Builder withYear(Integer year) {
            this.manufactureYear = year;
            return this;
        }

        public InstrumentEntity build() {
            return new InstrumentEntity(this);
        }
    }

}
