package com.yura.repair.repository.impl;

import com.yura.repair.entity.InstrumentEntity;
import com.yura.repair.repository.InstrumentRepository;
import com.yura.repair.repository.connector.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class InstrumentRepositoryImpl extends AbstractRepository<InstrumentEntity> implements InstrumentRepository {
    private static final String SAVE_QUERY = "INSERT INTO instruments(brand, model, manufacture_year) VALUES (?,?,?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM instruments WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM instruments";
    private static final String UPDATE_QUERY = "UPDATE instruments SET brand = ?, model = ?, manufacture_year = ? WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM instruments WHERE id = ?";
    private static final String COUNT_ALL_QUERY = "SELECT COUNT(*) FROM instruments";

    public InstrumentRepositoryImpl(DBConnector connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY);
    }

    @Override
    protected void insertStatementMapper(InstrumentEntity entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entity.getBrand());
        preparedStatement.setString(2, entity.getModel());
        preparedStatement.setInt(3, entity.getManufactureYear());
    }

    @Override
    protected void updateStatementMapper(InstrumentEntity entity, PreparedStatement preparedStatement) throws SQLException {
        insertStatementMapper(entity, preparedStatement);
        preparedStatement.setInt(4, entity.getId());
    }

    @Override
    protected Optional<InstrumentEntity> mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return Optional.of(InstrumentEntity.builder()
                .withId(resultSet.getInt("id"))
                .withBrand(resultSet.getString("brand"))
                .withModel(resultSet.getString("model"))
                .withYear(resultSet.getInt("manufacture_year"))
                .build());
    }

    @Override
    public Integer countAll() {
        return count(COUNT_ALL_QUERY);
    }
}
