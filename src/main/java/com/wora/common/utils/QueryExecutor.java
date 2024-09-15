package com.wora.common.utils;

import com.wora.common.infrastructure.persistence.SQLConsumer;
import com.wora.config.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class QueryExecutor {
    private final static Connection CONNECTION = DatabaseConnection.getInstance().getConnection();

    private QueryExecutor() {
    }

    public static void executeUpdatePreparedStatement(final String query, final SQLConsumer<PreparedStatement> executor) {
        try (final var stmt = CONNECTION.prepareStatement(query)) {
            executor.run(stmt);

            // after
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("database operation failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void executeQueryStatement(final String query, final SQLConsumer<ResultSet> executor) {
        try (final var stmt = CONNECTION.createStatement()) {
            final ResultSet resultSet = stmt.executeQuery(query);
            executor.run(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void executeQueryPreparedStatement(final String query, final SQLConsumer<PreparedStatement> executor) {
        try (final var stmt = CONNECTION.prepareStatement(query)) {
            executor.run(stmt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void execute(final String query, final SQLConsumer<PreparedStatement> executor) {
        try (final var stmt = CONNECTION.prepareStatement(query)) {
            executor.run(stmt);

            final int affectedRows = stmt.executeUpdate();
            assert affectedRows == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
