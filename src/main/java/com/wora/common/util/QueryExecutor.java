package com.wora.common.util;

import com.wora.common.infrastructure.persistence.SQLConsumer;
import com.wora.config.DatabaseConnection;

import javax.management.StringValueExp;
import java.sql.*;

public class QueryExecutor {
    private final static Connection CONNECTION = DatabaseConnection.getInstance().getConnection();

    private QueryExecutor() {
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
        Savepoint savepoint = null;
        try (final var stmt = CONNECTION.prepareStatement(query)) {
            savepoint = CONNECTION.setSavepoint();

            executor.run(stmt);

            CONNECTION.commit();
        } catch (SQLException e) {
            rollBack(savepoint);
            throw new RuntimeException(e);
        }
    }

    public static void executeWithSingleUpdate(final String query, final SQLConsumer<PreparedStatement> executor) {
        Savepoint savepoint = null;
        try (final var stmt = CONNECTION.prepareStatement(query)) {
            savepoint = CONNECTION.setSavepoint();

            executor.run(stmt);
            final int affectedRows = stmt.executeUpdate();
            assert affectedRows == 1;

            CONNECTION.commit();
        } catch (SQLException e) {
            rollBack(savepoint);
            throw new RuntimeException(e);
        }
    }

    private static void rollBack(final Savepoint savepoint) {
        try {
            CONNECTION.rollback(savepoint);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
