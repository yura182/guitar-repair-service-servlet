package com.yura.repair.dto;

import java.util.Objects;

public class InstrumentDto {
    private final Integer id;
    private final String brand;
    private final String model;
    private final Integer manufactureYear;

    public InstrumentDto(Builder builder) {
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
        InstrumentDto that = (InstrumentDto) obj;
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

        result.append("Instrument - ");
        result.append("id: ").append(id).append(", ");
        result.append("brand: ").append(brand).append(", ");
        result.append("model: ").append(model).append(", ");
        result.append("manufacture year: ").append(manufactureYear);

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

        public InstrumentDto build() {
            return new InstrumentDto(this);
        }
    }

}
