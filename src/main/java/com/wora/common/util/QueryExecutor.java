package com.wora.common.util;

import com.wora.common.infrastructure.persistence.SQLConsumer;
import com.wora.config.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryExecutor {
    private final static Connection CONNECTION = DatabaseConnection.getInstance().getConnection();

    private QueryExecutor() {
    }

    public static void executeUpdateWithPreparedStatement(final String query, final SQLConsumer<PreparedStatement> executor) {
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

    public static void fetchResultWithQuery(final String query, final SQLConsumer<ResultSet> executor) {
        try (final var stmt = CONNECTION.createStatement()) {
            final ResultSet resultSet = stmt.executeQuery(query);
            executor.run(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void executeQueryWithPreparedStatement(final String query, final SQLConsumer<PreparedStatement> executor) {
        try (final var stmt = CONNECTION.prepareStatement(query)) {
            executor.run(stmt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void executeWithSingleUpdate(final String query, final SQLConsumer<PreparedStatement> executor) {
        try (final var stmt = CONNECTION.prepareStatement(query)) {
            executor.run(stmt);

            final int affectedRows = stmt.executeUpdate();
            assert affectedRows == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
