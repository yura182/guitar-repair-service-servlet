package com.yura.repairservice.service.mapper;

import com.yura.repairservice.domain.User;
import com.yura.repairservice.entity.UserEntity;

import java.util.Objects;

public class UserMapper implements EntityMapper<UserEntity, User> {

    @Override
    public User mapEntityToDomain(UserEntity userEntity) {
        return Objects.isNull(userEntity) ? null : User.builder()
                .withId(userEntity.getId())
                .withName(userEntity.getName())
                .withSurname(userEntity.getSurname())
                .withEmail(userEntity.getEmail())
                .withPassword(userEntity.getPassword())
                .withPhone(userEntity.getPhone())
                .withRole(userEntity.getRole())
                .build();
    }

    @Override
    public UserEntity mapDomainToEntity(User user) {
        return Objects.isNull(user) ? null : UserEntity.builder()
                .withId(user.getId())
                .withName(user.getName())
                .withSurname(user.getSurname())
                .withEmail(user.getEmail())
                .withPassword(user.getPassword())
                .withPhone(user.getPhone())
                .withRole(user.getRole())
                .build();
    }
}
