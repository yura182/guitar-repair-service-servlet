package com.yura.repair.dto;

import com.yura.repair.entity.Role;

import java.util.Objects;

public class UserDto {
    private final Integer id;
    private final String name;
    private final String surname;
    private final String email;
    private final String password;
    private final String phone;
    private final Role role;

    public UserDto(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.email = builder.email;
        this.password = builder.password;
        this.phone = builder.phone;
        this.role = builder.role;
    }

    public UserDto(UserDto userDto, String encodedPassword) {
        this.id = userDto.id;
        this.name = userDto.name;
        this.surname = userDto.surname;
        this.email = userDto.email;
        this.password = encodedPassword;
        this.phone = userDto.phone;
        this.role = userDto.role;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) &&
                Objects.equals(name, userDto.name) &&
                Objects.equals(surname, userDto.surname) &&
                Objects.equals(email, userDto.email) &&
                Objects.equals(password, userDto.password) &&
                Objects.equals(phone, userDto.phone) &&
                role == userDto.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, password, phone, role);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("User - ");
        result.append("id: ").append(id).append(", ");
        result.append("name: ").append(name).append(", ");
        result.append("surname: ").append(surname).append(", ");
        result.append("phone: ").append(phone).append(", ");
        result.append("email: ").append(email).append(", ");
        result.append("role: ").append(role);

        return result.toString();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer id;
        private String name;
        private String surname;
        private String phone;
        private String email;
        private String password;
        private Role role;

        private Builder() {
        }

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withRole(Role role) {
            this.role = role;
            return this;
        }

        public UserDto build() {
            return new UserDto(this);
        }
    }
}
