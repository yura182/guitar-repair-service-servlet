package com.yura.repair.service.impl;

import com.yura.repair.entity.Role;
import com.yura.repair.dto.UserDto;
import com.yura.repair.entity.UserEntity;
import com.yura.repair.exception.AlreadyRegisteredUserException;
import com.yura.repair.exception.InvalidParameterException;
import com.yura.repair.exception.UserNotFoundException;
import com.yura.repair.repository.UserRepository;
import com.yura.repair.service.encoder.PasswordEncoder;
import com.yura.repair.service.mapper.EntityMapper;
import com.yura.repair.service.validator.Validator;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    private static final UserDto USER_DTO = getUserDto();
    private static final UserEntity USER_ENTITY = getUserEntity();
    private static final String PASSWORD = "12345678";
    private static final String EMAIL = "yura@gmail.com";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Mock
    private UserRepository userRepository;

    @Mock
    private EntityMapper<UserEntity, UserDto> userMapper;

    @Mock
    private Validator<UserDto> userValidator;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @After
    public void resetMocks() {
        reset(userRepository, userMapper, userValidator, passwordEncoder);
    }

    @Test
    public void registerShouldRegisterUser() {
        when(passwordEncoder.encode(USER_DTO.getPassword())).thenReturn(USER_DTO.getPassword());
        when(userRepository.findByEmail(USER_DTO.getEmail())).thenReturn(Optional.empty());
        when(userMapper.mapDomainToEntity(USER_DTO)).thenReturn(USER_ENTITY);

        userService.register(USER_DTO);
        verify(userRepository).save(USER_ENTITY);
    }

    @Test
    public void registerShouldThrowInvalidParameterExceptionForNullParameter() {
        exception.expect(InvalidParameterException.class);

        doThrow(InvalidParameterException.class).when(userValidator).validate(null);

        userService.register(null);
    }

    @Test
    public void registerShouldThrowAlreadyRegisteredException() {
        exception.expect(AlreadyRegisteredUserException.class);
        exception.expectMessage("User with such email already exist");

        when(userRepository.findByEmail(USER_DTO.getEmail())).thenReturn(Optional.of(USER_ENTITY));

        userService.register(USER_DTO);
    }

    @Test
    public void loginShouldLoginUserAndReturnLoggedUser() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(USER_ENTITY));
        when(passwordEncoder.encode(anyString())).thenReturn(USER_ENTITY.getPassword());
        when(userMapper.mapEntityToDomain(USER_ENTITY)).thenReturn(USER_DTO);

        UserDto actual = userService.login(USER_DTO.getEmail(), USER_DTO.getPassword());

        assertEquals(USER_DTO, actual);
    }

    @Test
    public void loginShouldThrowUserNotFoundExceptionWithIncorrectEmail() {
        exception.expect(UserNotFoundException.class);
        exception.expectMessage("User not found");

        when(userRepository.findByEmail(EMAIL)).thenReturn(Optional.empty());
        when(userMapper.mapEntityToDomain(USER_ENTITY)).thenReturn(USER_DTO);
        when(passwordEncoder.encode(PASSWORD)).thenReturn(PASSWORD);

        userService.login(EMAIL, PASSWORD);
    }

    @Test
    public void loginShouldThrowUserNotFoundExceptionWithIncorrectPassword() {
        exception.expect(UserNotFoundException.class);
        exception.expectMessage("User not found");

        when(userRepository.findByEmail(EMAIL)).thenReturn(Optional.of(USER_ENTITY));
        when(userMapper.mapEntityToDomain(USER_ENTITY)).thenReturn(USER_DTO);
        when(passwordEncoder.encode("Wrong password")).thenReturn("Encoded wrong password");

        userService.login(EMAIL, "Wrong password");
    }

    @Test
    public void findByIdShouldReturnUser() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(USER_ENTITY));
        when(userMapper.mapEntityToDomain(USER_ENTITY)).thenReturn(USER_DTO);

        UserDto actual = userService.findById(1);

        assertEquals(USER_DTO, actual);
    }

    @Test
    public void findByIdShouldThrowUserNotFoundException() {
        exception.expect(UserNotFoundException.class);
        exception.expectMessage("User not found with provided id");
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());

        userService.findById(1);
    }

    @Test
    public void findAllShouldReturnListOfUsers() {
        List<UserDto> expected = Collections.singletonList(USER_DTO);
        List<UserEntity> userEntities = Collections.singletonList(USER_ENTITY);

        when(userRepository.findAll(anyInt(), anyInt())).thenReturn(userEntities);
        when(userMapper.mapEntityToDomain(USER_ENTITY)).thenReturn(USER_DTO);

        List<UserDto> actual = userService.findAll(1, 5);

        assertEquals(expected, actual);
    }

    @Test
    public void findAllShouldReturnEmptyList() {
        List<UserDto> expected = Collections.emptyList();
        List<UserEntity> userEntities = Collections.emptyList();

        when(userRepository.findAll(anyInt(), anyInt())).thenReturn(userEntities);

        List<UserDto> actual = userService.findAll(1, 5);

        assertEquals(expected, actual);
    }

    @Test
    public void numberOfEntriesShouldReturnNumberOfEntries() {
        when(userRepository.countAll()).thenReturn(10);

        int expected = 10;
        int actual = userService.numberOfEntries();

        assertEquals(expected, actual);
    }


    private static UserDto getUserDto() {
        return UserDto.builder()
                .withName("Yura")
                .withSurname("Petrashenko")
                .withEmail(EMAIL)
                .withPassword(PASSWORD)
                .withPhone("0665005050")
                .withRole(Role.CLIENT)
                .build();
    }

    private static UserEntity getUserEntity() {
        return UserEntity.builder()
                .withId(1)
                .withName("Yura")
                .withSurname("Petrashenko")
                .withEmail(EMAIL)
                .withPassword(PASSWORD)
                .withPhone("0665005050")
                .withRole(Role.CLIENT)
                .build();
    }
}