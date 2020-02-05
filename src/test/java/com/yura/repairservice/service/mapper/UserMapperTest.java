package com.yura.repairservice.service.mapper;

import com.yura.repairservice.domain.user.Role;
import com.yura.repairservice.domain.user.User;
import com.yura.repairservice.entity.UserEntity;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserMapperTest {
    private static final User USER = getUser();
    private static final UserEntity USER_ENTITY = getUserEntity();

    private final EntityMapper<UserEntity, User> userMapper = new UserMapper();

    @Test
    public void mapEntityToDomainShouldMapToDomain() {
        User actual = userMapper.mapEntityToDomain(USER_ENTITY);

        assertEquals(USER, actual);
    }

    @Test
    public void mapDomainToEntityShouldMapToEntity() {
        UserEntity actual = userMapper.mapDomainToEntity(USER);

        assertEquals(USER_ENTITY, actual);
    }

    private static User getUser() {
        return User.builder()
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