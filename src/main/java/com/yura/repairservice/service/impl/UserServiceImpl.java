package com.yura.repairservice.service.impl;

import com.yura.repairservice.domain.User;
import com.yura.repairservice.exception.AlreadyRegisteredUserException;
import com.yura.repairservice.exception.EntityNotFoundException;
import com.yura.repairservice.repository.UserRepository;
import com.yura.repairservice.service.UserService;
import com.yura.repairservice.service.mapper.UserMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserServiceImpl(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void register(User user) {
       //TODO validate
        repository.findByEmail(user.getEmail()).ifPresent(u -> {
            LOGGER.warn("User with such email already exist " + u.getEmail());
            throw new AlreadyRegisteredUserException("User with such email already exist " + u.getEmail());
        });

        repository.save(mapper.mapUserToUserEntity(user));
        LOGGER.info("User registered " + user.getEmail());
    }

    @Override
    public User login(String email, String password) {
        return repository
                .findByEmail(email)
                .map(mapper::mapUserEntityToUser)
                .filter(user -> Objects.equals(user.getPassword(), password))
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<User> findAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::mapUserEntityToUser)
                .collect(Collectors.toList());
    }
}
