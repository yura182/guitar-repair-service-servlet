package com.yura.repairservice.repository.impl;

import com.yura.repairservice.exception.DBRuntimeException;
import com.yura.repairservice.repository.CrudRepository;
import com.yura.repairservice.repository.connector.DBConnector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractRepository<E> implements CrudRepository<E, Integer> {
    private static final Logger LOGGER = LogManager.getLogger(AbstractRepository.class);

    protected DBConnector connector;

    private final String saveQuery;
    private final String findByIdQuery;
    private final String findAllQuery;
    private final String updateQuery;
    private final String deleteQuery;

    protected AbstractRepository(DBConnector connector, String saveQuery, String findByIdQuery, String findAllQuery,
                                 String updateQuery, String deleteQuery) {

        this.connector = connector;
        this.saveQuery = saveQuery;
        this.findByIdQuery = findByIdQuery;
        this.findAllQuery = findAllQuery;
        this.updateQuery = updateQuery;
        this.deleteQuery = deleteQuery;
    }

    @Override
    public boolean save(E entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(saveQuery)) {

            insertStatementMapper(entity, preparedStatement);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.error("Could not save entity to database", e);
            throw new DBRuntimeException("Could not save entity to database", e);
        }
    }

    @Override
    public Optional<E> findById(Integer id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findByIdQuery)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? mapResultSetToEntity(resultSet) : Optional.empty();
        } catch (SQLException e) {
            LOGGER.error("Exception during searching entity", e);
            throw new DBRuntimeException("Exception during searching entity", e);
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            LOGGER.error("Exception during deleting entity", e);
            throw new DBRuntimeException("Exception during deleting entity", e);
        }
    }

    @Override
    public List<E> findAll(Integer offset, Integer limit) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findAllQuery)) {

            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, limit);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<E> result = new ArrayList<>();

            while (resultSet.next()) {
                mapResultSetToEntity(resultSet).ifPresent(result::add);
            }
            return result;
        } catch (SQLException e) {
            LOGGER.error("Exception during fetching all entities", e);
            throw new DBRuntimeException("Exception during fetching all entities", e);
        }
    }

    protected List<E> findAllById(Integer id, String query, Integer offset, Integer limit) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, offset);
            preparedStatement.setInt(3, limit);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<E> result = new ArrayList<>();

            while (resultSet.next()) {
                mapResultSetToEntity(resultSet).ifPresent(result::add);
            }
            return result;
        } catch (SQLException e) {
            LOGGER.error("Exception during fetching all entities by id");
            throw new DBRuntimeException("Exception during fetching all entities by id");
        }
    }

    public Optional<Integer> saveAndReturnId(E entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(saveQuery, Statement.RETURN_GENERATED_KEYS)) {

            insertStatementMapper(entity, preparedStatement);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            return resultSet.next() ? Optional.of(resultSet.getInt(1)) : Optional.empty();
        } catch (SQLException e) {
            LOGGER.error("Could not save entity to database", e);
            throw new DBRuntimeException("Could not save entity to database", e);
        }
    }

    protected List<E> findAllByStringParam(String parameter, String query, Integer offset, Integer limit) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, parameter);
            preparedStatement.setInt(2, offset);
            preparedStatement.setInt(3, limit);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<E> result = new ArrayList<>();

            while (resultSet.next()) {
                mapResultSetToEntity(resultSet).ifPresent(result::add);
            }
            return result;
        } catch (SQLException e) {
            LOGGER.error("Exception during fetching all entities by string parameter");
            throw new DBRuntimeException("Exception during fetching all entities by string parameter");
        }
    }

    @Override
    public boolean update(E entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            updateStatementMapper(entity, preparedStatement);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            LOGGER.error("Exception during updating entity", e);
            throw new DBRuntimeException("Exception during updating entity", e);
        }
    }

    protected Optional<E> findByStringParam(String param, String query) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, param);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? mapResultSetToEntity(resultSet) : Optional.empty();
        } catch (SQLException e) {
            LOGGER.error("Exception during searching entity by string param", e);
            e.printStackTrace();
            throw new DBRuntimeException("Exception during searching entity by string param", e);
        }
    }

    protected Integer count(String query) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet resultSet = statement.executeQuery();

            return resultSet.next() ? resultSet.getInt(1) : 0;
        } catch (SQLException e) {
            LOGGER.error("Exception during counting entities", e);
            throw new DBRuntimeException("Exception during counting entities", e);
        }
    }

    protected Integer countByIntegerParam(String query, Integer parameter) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, parameter);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next() ? resultSet.getInt(1) : 0;
        } catch (SQLException e) {
            LOGGER.error("Exception during counting entities", e);
            throw new DBRuntimeException("Exception during counting entities", e);
        }
    }

    protected Integer countByStringParam(String query, String parameter) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, parameter);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next() ? resultSet.getInt(1) : 0;
        } catch (SQLException e) {
            LOGGER.error("Exception during counting entities", e);
            throw new DBRuntimeException("Exception during counting entities", e);
        }
    }

    protected abstract void insertStatementMapper(E entity, PreparedStatement preparedStatement) throws SQLException;

    protected abstract void updateStatementMapper(E entity, PreparedStatement preparedStatement) throws SQLException;

    protected abstract Optional<E> mapResultSetToEntity(ResultSet resultSet) throws SQLException;
}
