package com.yura.repair.repository.connector;

import com.yura.repair.exception.DBRuntimeException;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBConnector {
    private static final Logger LOGGER = LogManager.getLogger(DBConnector.class);

    private static final String DB_DRIVER_KEY = "db.driver";
    private static final String DB_URL_KEY = "db.url";
    private static final String DB_USER_KEY = "db.user";
    private static final String DB_PASSWORD_KEY = "db.password";
    private static final String DB_MIN_IDLE_KEY = "db.minIdle";
    private static final String DB_MAX_IDLE_KEY = "db.maxIdle";
    private static final String DB_MAX_OPEN_STATEMENT_KEY = "db.maxOpenStatement";

    private final BasicDataSource pool;

    public DBConnector(String configFileName, BasicDataSource pool) {
        this.pool = pool;
        ResourceBundle bundle = ResourceBundle.getBundle(configFileName);

        pool.setDriverClassName(bundle.getString(DB_DRIVER_KEY));
        pool.setUrl(bundle.getString(DB_URL_KEY));
        pool.setUsername(bundle.getString(DB_USER_KEY));
        pool.setPassword(bundle.getString(DB_PASSWORD_KEY));
        pool.setMinIdle(Integer.parseInt(bundle.getString(DB_MIN_IDLE_KEY)));
        pool.setMaxIdle(Integer.parseInt(bundle.getString(DB_MAX_IDLE_KEY)));
        pool.setMaxOpenPreparedStatements(Integer.parseInt(bundle.getString(DB_MAX_OPEN_STATEMENT_KEY)));
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
