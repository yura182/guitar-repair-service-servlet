package com.yura.repairservice.repository.connector;

import com.yura.repairservice.exception.DBRuntimeException;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBConnector {
    private static final Logger LOGGER = LogManager.getLogger(DBConnector.class);

    private final BasicDataSource pool;

    public DBConnector(String configFileName, BasicDataSource pool) {
        this.pool = pool;
        ResourceBundle bundle = ResourceBundle.getBundle(configFileName);

        pool.setDriverClassName(bundle.getString("db.driver"));
        pool.setUrl(bundle.getString("db.url"));
        pool.setUsername(bundle.getString("db.user"));
        pool.setPassword(bundle.getString("db.password"));
        pool.setMinIdle(Integer.parseInt(bundle.getString("db.minIdle")));
        pool.setMaxIdle(Integer.parseInt(bundle.getString("db.maxIdle")));
        pool.setMaxOpenPreparedStatements(Integer.parseInt(bundle.getString("db.maxOpenStatement")));
    }

    public Connection getConnection() {
        try {
            return pool.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Could not establish a database connection", e);
            throw new DBRuntimeException("Could not establish a database connection", e);
        }
    }
}
