package com.yura.repair.service.mapper;

import com.yura.repair.dto.UserDto;
import com.yura.repair.entity.UserEntity;

import java.util.Objects;

public class UserMapper implements EntityMapper<UserEntity, UserDto> {

    @Override
    public UserDto mapEntityToDomain(UserEntity userEntity) {
        return Objects.isNull(userEntity) ? null : UserDto.builder()
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
    public UserEntity mapDomainToEntity(UserDto userDto) {
        return Objects.isNull(userDto) ? null : UserEntity.builder()
                .withId(userDto.getId())
                .withName(userDto.getName())
                .withSurname(userDto.getSurname())
                .withEmail(userDto.getEmail())
                .withPassword(userDto.getPassword())
                .withPhone(userDto.getPhone())
                .withRole(userDto.getRole())
                .build();
    }
}
