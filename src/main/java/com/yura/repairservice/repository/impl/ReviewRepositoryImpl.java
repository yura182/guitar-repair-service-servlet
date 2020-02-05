package com.yura.repairservice.repository.impl;

import com.yura.repairservice.entity.OrderEntity;
import com.yura.repairservice.entity.ReviewEntity;
import com.yura.repairservice.entity.UserEntity;
import com.yura.repairservice.repository.ReviewRepository;
import com.yura.repairservice.repository.connector.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public class ReviewRepositoryImpl extends AbstractRepository<ReviewEntity> implements ReviewRepository {
    private static final String SAVE_QUERY = "INSERT INTO reviews(client_id, order_id, text, date) VALUES (?,?,?,?)";
    private static final String FIND_ALL = "SELECT " +
            "r.id as review_id, r.client_id, r.order_id, r.text, r.date, " +
            "u.name, u.surname, u.email " +
            "FROM reviews as r " +
            "LEFT JOIN users as u ON r.client_id = u.id ORDER BY r.id DESC";
    private static final String LIMIT = " LIMIT ?, ?";
    private static final String FIND_ALL_QUERY = FIND_ALL + LIMIT;
    private static final String FIND_BY_ID_QUERY = FIND_ALL_QUERY + " WHERE r.id = ?";
    private static final String UPDATE_QUERY = "UPDATE reviews SET client_id = ?, order_id = ?, text = ?, date = ? WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM reviews WHERE id = ?";
    private static final String FIND_ALL_BY_ORDER = FIND_ALL_QUERY + " WHERE order_id = ?";
    private static final String FIND_ALL_BY_CLIENT = FIND_ALL_QUERY + " WHERE client_id = ?";
    private static final String COUNT_ALL_QUERY = "SELECT COUNT(*) FROM reviews";
    private static final String COUNT_ALL_BY_ORDER_QUERY = "SELECT COUNT(*) FROM reviews WHERE order_id = ?";
    private static final String COUNT_ALL_BY_CLIENT_QUERY = "SELECT COUNT(*) FROM reviews WHERE client_id = ?";

    public ReviewRepositoryImpl(DBConnector connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY);
    }

    @Override
    public List<ReviewEntity> findAllByOrder(Integer orderId, Integer offset, Integer limit) {
        return findAllById(orderId, FIND_ALL_BY_ORDER, offset, limit);
    }

    @Override
    public List<ReviewEntity> findAllByClient(Integer clientId, Integer offset, Integer limit) {
        return findAllById(clientId, FIND_ALL_BY_CLIENT, offset, limit);
    }

    @Override
    protected void insertStatementMapper(ReviewEntity entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, entity.getClient().getId());
        preparedStatement.setInt(2, entity.getOrder().getId());
        preparedStatement.setString(3, entity.getText());
        preparedStatement.setTimestamp(4, Timestamp.valueOf(entity.getDate()));
    }

    @Override
    protected void updateStatementMapper(ReviewEntity entity, PreparedStatement preparedStatement) throws SQLException {
        insertStatementMapper(entity, preparedStatement);
        preparedStatement.setInt(5, entity.getId());
    }

    @Override
    protected Optional<ReviewEntity> mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return Optional.of(ReviewEntity.builder()
                .withId(resultSet.getInt("review_id"))
                .withClient(UserEntity.builder()
                        .withId(resultSet.getInt("client_id"))
                        .withName(resultSet.getString("name"))
                        .withSurname(resultSet.getString("surname"))
                        .withEmail(resultSet.getString("email"))
                        .build())
                .withOrder(OrderEntity.builder().
                        withId(resultSet.getInt("order_id"))
                        .build())
                .withText(resultSet.getString("text"))
                .withDate(resultSet.getTimestamp("date").toLocalDateTime())
                .build());
    }

    @Override
    public Integer countAll() {
        return count(COUNT_ALL_QUERY);
    }

    @Override
    public Integer countByOrderId(Integer orderId) {
        return countByIntegerParam(COUNT_ALL_BY_ORDER_QUERY, orderId);
    }

    @Override
    public Integer countByClientId(Integer clientId) {
        return countByIntegerParam(COUNT_ALL_BY_CLIENT_QUERY, clientId);
    }
}
