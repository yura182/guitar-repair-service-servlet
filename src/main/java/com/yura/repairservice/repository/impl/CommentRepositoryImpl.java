package com.yura.repairservice.repository.impl;

import com.yura.repairservice.entity.CommentEntity;
import com.yura.repairservice.repository.CommentRepository;
import com.yura.repairservice.repository.connector.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CommentRepositoryImpl extends AbstractRepository<CommentEntity> implements CommentRepository {
    private static final String SAVE_QUERY = "INSERT INTO comments(client_id, order_id, text) VALUES (?,?,?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM comments WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM comments";
    private static final String UPDATE_QUERY = "UPDATE comments SET client_id = ?, order_id = ?, text = ? WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM comments WHERE id = ?";
    private static final String FIND_ALL_BY_ORDER = "SELECT * FROM comments WHERE order_id = ?";
    private static final String FIND_ALL_BY_CLIENT = "SELECT * FROM comments WHERE client_id = ?";

    protected CommentRepositoryImpl(DBConnector connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY);
    }

    @Override
    public List<CommentEntity> findAllByOrder(Integer orderId) {
        return null;
    }

    @Override
    public List<CommentEntity> findAllByClient(Integer clientId) {
        return null;
    }

    @Override
    protected void insertStatementMapper(CommentEntity entity, PreparedStatement preparedStatement) throws SQLException {

    }

    @Override
    protected void updateStatementMapper(CommentEntity entity, PreparedStatement preparedStatement) throws SQLException {

    }

    @Override
    protected Optional<CommentEntity> mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return Optional.empty();
    }
}
