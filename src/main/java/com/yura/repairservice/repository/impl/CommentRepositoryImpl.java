package com.yura.repairservice.repository.impl;

import com.yura.repairservice.entity.CommentEntity;
import com.yura.repairservice.entity.OrderEntity;
import com.yura.repairservice.entity.UserEntity;
import com.yura.repairservice.repository.CommentRepository;
import com.yura.repairservice.repository.connector.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public class CommentRepositoryImpl extends AbstractRepository<CommentEntity> implements CommentRepository {
    private static final String SAVE_QUERY = "INSERT INTO comments(client_id, order_id, text, date) VALUES (?,?,?,?)";
    private static final String FIND_ALL_QUERY = "SELECT " +
            "c.id as comments_id, c.client_id, c.order_id, c.text, c.date, " +
            "u.name, u.surname, u.email " +
            "FROM comments as c " +
            "LEFT JOIN users as u ON c.client_id = u.id";
    private static final String FIND_BY_ID_QUERY = FIND_ALL_QUERY + " WHERE c.id = ?";
    private static final String UPDATE_QUERY = "UPDATE comments SET client_id = ?, order_id = ?, text = ?, date = ? WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM comments WHERE id = ?";
    private static final String FIND_ALL_BY_ORDER = FIND_ALL_QUERY + " WHERE order_id = ?";
    private static final String FIND_ALL_BY_CLIENT = FIND_ALL_QUERY + " WHERE client_id = ?";

    public CommentRepositoryImpl(DBConnector connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY);
    }

    @Override
    public List<CommentEntity> findAllByOrder(Integer orderId) {
        return findAllById(orderId, FIND_ALL_BY_ORDER);
    }

    @Override
    public List<CommentEntity> findAllByClient(Integer clientId) {
        return findAllById(clientId, FIND_ALL_BY_CLIENT);
    }

    @Override
    protected void insertStatementMapper(CommentEntity entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, entity.getClient().getId());
        preparedStatement.setInt(2, entity.getOrder().getId());
        preparedStatement.setString(3, entity.getText());
        preparedStatement.setTimestamp(4, Timestamp.valueOf(entity.getDate()));
    }

    @Override
    protected void updateStatementMapper(CommentEntity entity, PreparedStatement preparedStatement) throws SQLException {
        insertStatementMapper(entity, preparedStatement);
        preparedStatement.setInt(5, entity.getId());
    }

    @Override
    protected Optional<CommentEntity> mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return Optional.ofNullable(CommentEntity.builder()
                .withId(resultSet.getInt("comments_id"))
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
}
