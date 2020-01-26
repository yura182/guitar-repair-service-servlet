package com.yura.repairservice.repository.impl;

import com.yura.repairservice.repository.UserRepository;
import com.yura.repairservice.repository.connector.DBConnector;
import com.yura.repairservice.domain.user.Role;
import com.yura.repairservice.entity.UserEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserRepositoryImpl extends AbstractRepository<UserEntity> implements UserRepository {
    private static final String SAVE_QUERY = "INSERT INTO users(name, surname, phone_number, email, password, role) VALUES (?,?,?,?,?,?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM users WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM users LIMIT ?, ?";
    private static final String UPDATE_QUERY = "UPDATE users SET name = ?, surname = ?, phone_number = ?, email = ?, password = ?, role = ? WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM users WHERE id = ?";
    private static final String FIND_BY_EMAIL_QUERY = "SELECT * FROM users WHERE email = ?";
    private static final String COUNT_ALL_QUERY = "SELECT COUNT(*) FROM users";

    public UserRepositoryImpl(DBConnector connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return findByStringParam(email, FIND_BY_EMAIL_QUERY);
    }

    @Override
    protected void insertStatementMapper(UserEntity entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setString(2, entity.getSurname());
        preparedStatement.setString(3, entity.getPhone());
        preparedStatement.setString(4, entity.getEmail());
        preparedStatement.setString(5, entity.getPassword());
        preparedStatement.setString(6, entity.getRole().toString());
    }

    @Override
    protected void updateStatementMapper(UserEntity entity, PreparedStatement preparedStatement) throws SQLException {
        insertStatementMapper(entity, preparedStatement);
        preparedStatement.setInt(7, entity.getId());
    }

    @Override
    protected Optional<UserEntity> mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return Optional.ofNullable(UserEntity.builder()
                .withId(resultSet.getInt("id"))
                .withName(resultSet.getString("name"))
                .withSurname(resultSet.getString("surname"))
                .withPhone(resultSet.getString("phone_number"))
                .withEmail(resultSet.getString("email"))
                .withPassword(resultSet.getString("password"))
                .withRole(Role.valueOf(resultSet.getString("role")))
                .build());
    }

    @Override
    public Integer countAll() {
        return count(COUNT_ALL_QUERY);
    }
}
