package com.wora.common.infrastructure.mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public interface BaseEntityResultSetMapper<Entity> {
    Entity map(ResultSet rs) throws SQLException;

    void map(Entity entity, PreparedStatement stmt) throws SQLException;

    default LocalDateTime getDate(final String columnName, final ResultSet rs) throws SQLException {
        Timestamp timestamp = rs.getTimestamp(columnName);
        return timestamp != null ? timestamp.toLocalDateTime() : null;
    }
}
