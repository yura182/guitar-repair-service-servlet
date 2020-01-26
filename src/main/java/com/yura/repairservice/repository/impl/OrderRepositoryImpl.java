package com.yura.repairservice.repository.impl;

import com.yura.repairservice.domain.order.Status;
import com.yura.repairservice.entity.InstrumentEntity;
import com.yura.repairservice.entity.OrderEntity;
import com.yura.repairservice.entity.UserEntity;
import com.yura.repairservice.repository.OrderRepository;
import com.yura.repairservice.repository.connector.DBConnector;

import java.sql.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class OrderRepositoryImpl extends AbstractRepository<OrderEntity> implements OrderRepository {
    private static final String SAVE_QUERY = "INSERT INTO orders(master_id, client_id, instrument_id, date, service, price, status, rejection_reason) VALUES (?,?,?,?,?,?,?,?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM orders WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM orders";
    private static final String UPDATE_QUERY = "UPDATE orders SET master_id = ?, client_id = ?, instrument_id = ?, date = ?, service = ?, price = ?, status = ?, rejection_reason = ? WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM orders WHERE id = ?";
    private static final String FIND_ALL_BY_CLIENT = "SELECT * FROM orders WHERE client_id = ?";
    private static final String FIND_ALL_BY_MASTER = "SELECT * FROM orders WHERE master_id = ?";
    private static final String FIND_ALL_BY_STATUS = "SELECT * FROM orders WHERE status = ?";

    public OrderRepositoryImpl(DBConnector connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY);
    }

    @Override
    public List<OrderEntity> findAllByClientId(Integer clientId) {
        return findAllById(clientId, FIND_ALL_BY_CLIENT);
    }

    @Override
    public List<OrderEntity> findAllByMasterId(Integer masterId) {
        return findAllById(masterId, FIND_ALL_BY_MASTER);
    }

    @Override
    public List<OrderEntity> findAllByStatus(Status status) {
        return findAllByStringParam(status.toString(), FIND_ALL_BY_STATUS);
    }

    @Override
    protected void insertStatementMapper(OrderEntity entity, PreparedStatement preparedStatement) throws SQLException {
        System.out.println(entity);
        preparedStatement.setInt(2, entity.getClient().getId());
        preparedStatement.setInt(3, entity.getInstrument().getId());
        preparedStatement.setTimestamp(4, Timestamp.valueOf(entity.getDateTime()));
        preparedStatement.setString(5, entity.getService());
        preparedStatement.setString(7, entity.getStatus().toString());
        preparedStatement.setString(8, entity.getRejectionReason());

        if (Objects.isNull(entity.getMaster())) {
            preparedStatement.setNull(1, Types.INTEGER);
        } else {
            preparedStatement.setInt(1, entity.getMaster().getId());
        }

        if (Objects.isNull(entity.getPrice())) {
            preparedStatement.setNull(6, Types.DOUBLE);
        } else {
            preparedStatement.setDouble(6, entity.getPrice());
        }
    }

    @Override
    protected void updateStatementMapper(OrderEntity entity, PreparedStatement preparedStatement) throws SQLException {
        insertStatementMapper(entity, preparedStatement);
        preparedStatement.setInt(9, entity.getId());
    }

    @Override
    protected Optional<OrderEntity> mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return Optional.ofNullable(OrderEntity.builder()
                .withId(resultSet.getInt("id"))
                .withUser(UserEntity.builder().withId(resultSet.getInt("client_id")).build())
                .withMaster(UserEntity.builder().withId(resultSet.getInt("master_id")).build())
                .withInstrument(InstrumentEntity.builder().withId(resultSet.getInt("instrument_id")).build())
                .withDate(resultSet.getTimestamp("date").toLocalDateTime())
                .withService(resultSet.getString("service"))
                .withPrice(resultSet.getDouble("price"))
                .withStatus(Status.valueOf(resultSet.getString("status")))
                .withRejectionReason(resultSet.getString("rejection_reason"))
                .build());
    }
}
