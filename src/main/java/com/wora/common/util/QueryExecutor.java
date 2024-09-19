package com.wora.common.util;

import com.wora.common.infrastructure.persistence.SQLConsumer;
import com.wora.config.DatabaseConnection;

import java.sql.*;
import java.util.concurrent.atomic.AtomicBoolean;

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
            isAffectedRows(stmt);

            CONNECTION.commit();
        } catch (SQLException e) {
            rollBack(savepoint);
            throw new RuntimeException(e);
        }
    }

    public static <Value> boolean executeQueryReturnBoolean(final String query, Value value) {
        final AtomicBoolean exists = new AtomicBoolean(false);
        executeQueryWithPreparedStatement(query, stmt -> {
            stmt.setObject(1, value);
            final ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                exists.set(rs.getBoolean(1));
            }
        });
        return exists.get();
    }

    public static void isAffectedRows(PreparedStatement stmt) throws SQLException {
        final int affectedRows = stmt.executeUpdate();
        if (affectedRows == 0) {
            throw new RuntimeException("error while deleting this");
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
