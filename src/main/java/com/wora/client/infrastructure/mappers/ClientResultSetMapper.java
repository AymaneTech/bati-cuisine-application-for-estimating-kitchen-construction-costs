package com.wora.client.infrastructure.mappers;

import com.wora.client.domain.entities.Client;
import com.wora.client.domain.valueObjects.ClientId;
import com.wora.client.domain.valueObjects.Name;
import com.wora.common.infrastructure.mappers.BaseEntityResultSetMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ClientResultSetMapper implements BaseEntityResultSetMapper<Client> {

    public Client map(final ResultSet rs) throws SQLException {
        return new Client(
                new ClientId((UUID) rs.getObject("id")),
                new Name(
                        rs.getString("first_name"),
                        rs.getString("last_name")
                ),
                rs.getString("phone"),
                rs.getString("address"),
                rs.getBoolean("is_professional"),
                List.of(),
                rs.getTimestamp("created_at").toLocalDateTime(),
                getDate("updated_at", rs),
                getDate("deleted_at", rs)
        );
    }

    public void map(final Client client, final PreparedStatement stmt) throws SQLException {
        int count = 1;
        stmt.setString(count++, client.getName().firstName());
        stmt.setString(count++, client.getName().lastName());
        stmt.setString(count++, client.getPhone());
        stmt.setString(count++, client.getAddress());
        stmt.setBoolean(count++, client.getIsProfessional());
        stmt.setObject(count++, client.getId().value());
    }

    private LocalDateTime getDate(final String columnName, final ResultSet rs) throws SQLException {
        Timestamp timestamp = rs.getTimestamp(columnName);
        return timestamp != null ? timestamp.toLocalDateTime() : null;
    }
}