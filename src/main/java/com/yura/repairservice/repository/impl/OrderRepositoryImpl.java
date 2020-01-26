package com.yura.repairservice.repository.impl;

import com.yura.repairservice.domain.order.Status;
import com.yura.repairservice.domain.user.Role;
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
    private static final String SAVE_QUERY = "INSERT INTO orders(master_id, client_id, instrument_id, date, service," +
            " price, status, rejection_reason) VALUES (?,?,?,?,?,?,?,?)";
    private static final String FIND_BY_ID_QUERY = "SELECT " +
            "o.id, o.date, o.service, o.price, o.status, o.rejection_reason, o.instrument_id, " +
            "c.id as client_id, c.name as client_name, c.surname as client_surname, c.phone_number as " +
            "client_phone_number, c.email as client_email, c.password as client_password, c.role as client_role, " +
            "m.id as master_id, m.name as master_name, m.surname as master_surname, m.phone_number as " +
            "master_phone_number, m.email as master_email, m.password as master_password, m.role as master_role," +
            "i.brand, i.model, i.manufacture_year " +
            "FROM orders as o " +
            "LEFT JOIN users as c ON o.client_id = c.id " +
            "LEFT JOIN users as m ON o.master_id = m.id " +
            "LEFT JOIN instruments as i ON o.instrument_id = i.id " +
            "WHERE o.id = ?";
    private static final String FIND_ALL_QUERY = "SELECT " +
            "o.id, o.date, o.service, o.price, o.status, o.rejection_reason, o.instrument_id, " +
            "c.id as client_id, c.name as client_name, c.surname as client_surname, c.phone_number " +
            "as client_phone_number, c.email as client_email, c.password as client_password, c.role as client_role, " +
            "m.id as master_id, m.name as master_name, m.surname as master_surname, m.phone_number as " +
            "master_phone_number, m.email as master_email, m.password as master_password, m.role as master_role, " +
            "i.brand, i.model, i.manufacture_year " +
            "FROM orders as o " +
            "LEFT JOIN users as c ON o.client_id = c.id " +
            "LEFT JOIN users as m ON o.master_id = m.id " +
            "LEFT JOIN instruments as i ON o.instrument_id = i.id";
    private static final String UPDATE_QUERY = "UPDATE orders SET master_id = ?, client_id = ?, instrument_id = ?," +
            " date = ?, service = ?, price = ?, status = ?, rejection_reason = ? WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM orders WHERE id = ?";
    private static final String FIND_ALL_BY_CLIENT = FIND_ALL_QUERY + " WHERE o.client_id = ?";
    private static final String FIND_ALL_BY_MASTER = FIND_ALL_QUERY + " WHERE o.master_id = ?";
    private static final String FIND_ALL_BY_STATUS = FIND_ALL_QUERY + " WHERE o.status = ?";
    private static final String COUNT_ALL_QUERY = "SELECT COUNT(*) FROM orders";
    private static final String COUNT_ALL_BY_CLIENT_QUERY = "SELECT COUNT(*) FROM orders WHERE client_id = ?";
    private static final String COUNT_ALL_BY_MASTER_QUERY = "SELECT COUNT(*) FROM orders WHERE master_id = ?";
    private static final String COUNT_ALL_BY_STATUS_QUERY = "SELECT COUNT(*) FROM orders WHERE status = ?";

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
                .withUser(UserEntity.builder()
                        .withId(resultSet.getInt("client_id"))
                        .withName(resultSet.getString("client_name"))
                        .withSurname(resultSet.getString("client_surname"))
                        .withEmail(resultSet.getString("client_email"))
                        .withPhone(resultSet.getString("client_phone_number"))
                        .withRole(Role.valueOf(resultSet.getString("client_role")))
                        .build())
                .withMaster(resultSet.getObject("master_id") == null ? null : UserEntity.builder()
                        .withId(resultSet.getInt("master_id"))
                        .withName(resultSet.getString("master_name"))
                        .withSurname(resultSet.getString("master_surname"))
                        .withEmail(resultSet.getString("master_email"))
                        .withPhone(resultSet.getString("master_phone_number"))
                        .withRole(Role.valueOf(resultSet.getString("master_role")))
                        .build())
                .withInstrument(InstrumentEntity.builder()
                        .withId(resultSet.getInt("instrument_id"))
                        .withBrand(resultSet.getString("brand"))
                        .withModel(resultSet.getString("model"))
                        .withYear(resultSet.getInt("manufacture_year"))
                        .build())
                .withDate(resultSet.getTimestamp("date").toLocalDateTime())
                .withService(resultSet.getString("service"))
                .withPrice(resultSet.getDouble("price"))
                .withStatus(Status.valueOf(resultSet.getString("status")))
                .withRejectionReason(resultSet.getString("rejection_reason"))
                .build());
    }

    @Override
    public Integer countAll() {
        return count(COUNT_ALL_QUERY);
    }

    @Override
    public Integer countByClientId(Integer clientId) {
        return countByIntegerParam(COUNT_ALL_BY_CLIENT_QUERY, clientId);
    }

    @Override
    public Integer countByMasterId(Integer masterId) {
        return countByIntegerParam(COUNT_ALL_BY_MASTER_QUERY, masterId);
    }

    @Override
    public Integer countByStatus(Status status) {
        return countByStringParam(COUNT_ALL_BY_STATUS_QUERY, status.toString());
    }
}
