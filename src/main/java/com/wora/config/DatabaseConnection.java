package com.wora.config;

import com.wora.common.util.Env;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance = null;
    private Connection connection = null;

    private DatabaseConnection() throws SQLException {
        init();
    }

    public static DatabaseConnection getInstance() {
        synchronized (DatabaseConnection.class) {
            try {
                if (instance == null || !instance.connection.isClosed()) {
                    instance = new DatabaseConnection();
                }
                return instance;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void closeConnection() {
        if (instance != null) {
            try {
                instance.getConnection().close();
                instance = null;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void init() throws SQLException {
        final String url = Env.get("DB_URL");
        final String username = Env.get("DB_USERNAME");
        final String password = Env.get("DB_PASSWORD");
        try {
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
