package com.yura.repair.service.impl;

import com.yura.repair.dto.UserDto;
import com.yura.repair.entity.UserEntity;
import com.yura.repair.exception.AlreadyRegisteredUserException;
import com.yura.repair.exception.UserNotFoundException;
import com.yura.repair.repository.UserRepository;
import com.yura.repair.service.UserService;
import com.yura.repair.service.encoder.PasswordEncoder;
import com.yura.repair.service.mapper.EntityMapper;
import com.yura.repair.service.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    private final UserRepository repository;
    private final EntityMapper<UserEntity, UserDto> mapper;
    private final Validator<UserDto> validator;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, EntityMapper<UserEntity, UserDto> mapper, Validator<UserDto> validator, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(UserDto userDto) {
        validator.validate(userDto);

        repository.findByEmail(userDto.getEmail()).ifPresent(u -> {
            throw new AlreadyRegisteredUserException("User with such email already exist " + u.getEmail());
        });

        UserDto userWithEncodedPassword = new UserDto(userDto, passwordEncoder.encode(userDto.getPassword()));

        repository.save(mapper.mapDomainToEntity(userWithEncodedPassword));
        LOGGER.info("User registered " + userDto.getEmail());
    }

    @Override
    public UserDto login(String email, String password) {
        return repository
                .findByEmail(email)
                .map(mapper::mapEntityToDomain)
                .filter(user -> Objects.equals(user.getPassword(), passwordEncoder.encode(password)))
                .orElseThrow(() -> new UserNotFoundException("User not found with " + email + " email and provided password"));
    }

    @Override
    public UserDto findById(Integer id) {
        return repository
                .findById(id)
                .map(mapper::mapEntityToDomain)
                .orElseThrow(() -> new UserNotFoundException("User not found with provided id " + id));
    }

    @Override
    public List<UserDto> findAll(Integer offset, Integer limit) {
        return repository
                .findAll(offset, limit)
                .stream()
                .map(mapper::mapEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Integer numberOfEntries() {
        return repository.countAll();
    }
}
