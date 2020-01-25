package com.yura.repairservice.domain;

import java.util.Objects;

public class User {
    private final Integer id;
    private final String name;
    private final String surname;
    private final String email;
    private final String password;
    private final String phone;
    private final Role role;

    public User(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.email = builder.email;
        this.password = builder.password;
        this.phone = builder.phone;
        this.role = builder.role;
    }

    public User(User user, String encodedPassword) {
        this.id = user.id;
        this.name = user.name;
        this.surname = user.surname;
        this.email = user.email;
        this.password = encodedPassword;
        this.phone = user.phone;
        this.role = user.role;
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
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(phone, user.phone) &&
                role == user.role;
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

        public User build() {
            return new User(this);
        }
    }
}
