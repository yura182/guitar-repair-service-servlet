package com.yura.repair.service.mapper;

import com.yura.repair.entity.Role;
import com.yura.repair.dto.UserDto;
import com.yura.repair.entity.UserEntity;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserMapperTest {
    private static final UserDto USER_DTO = getUserDto();
    private static final UserEntity USER_ENTITY = getUserEntity();

    private final EntityMapper<UserEntity, UserDto> userMapper = new UserMapper();

    @Test
    public void mapEntityToDomainShouldMapToDomain() {
        UserDto actual = userMapper.mapEntityToDomain(USER_ENTITY);

        assertEquals(USER_DTO, actual);
    }

    @Test
    public void mapDomainToEntityShouldMapToEntity() {
        UserEntity actual = userMapper.mapDomainToEntity(USER_DTO);

        assertEquals(USER_ENTITY, actual);
    }

    @Test
    public void mapDomainToEntityShouldReturnNull() {
        UserEntity actual = userMapper.mapDomainToEntity(null);

        assertNull(actual);
    }

    @Test
    public void mapEntityToDomainShouldReturnNull() {
        UserDto actual = userMapper.mapEntityToDomain(null);

        assertNull(actual);
    }

    private static UserDto getUserDto() {
        return UserDto.builder()
                .withId(1)
                .withName("Yura")
                .withSurname("Petrashenko")
                .withEmail("yura@gmail.com")
                .withPassword("12345678")
                .withPhone("0665005050")
                .withRole(Role.CLIENT)
                .build();
    }

    private static UserEntity getUserEntity() {
        return UserEntity.builder()
                .withId(1)
                .withName("Yura")
                .withSurname("Petrashenko")
                .withEmail("yura@gmail.com")
                .withPassword("12345678")
                .withPhone("0665005050")
                .withRole(Role.CLIENT)
                .build();
    }
}