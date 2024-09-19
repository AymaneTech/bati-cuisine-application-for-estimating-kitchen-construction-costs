package com.wora.common.infrastructure.mapper;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface BaseEntityResultSetMapper<Entity> {
    Entity map(ResultSet rs) throws SQLException;

    void map(Entity entity, PreparedStatement stmt) throws SQLException;

    default LocalDateTime getDateTime(final String columnName, final ResultSet rs) throws SQLException {
        final Timestamp timestamp = rs.getTimestamp(columnName);
        return timestamp != null ? timestamp.toLocalDateTime() : null;
    }

    default LocalDate getDate(final String columnName, final ResultSet rs) throws SQLException {
        Date date = rs.getDate(columnName);
        return date != null ? date.toLocalDate() : null;
    }
}
