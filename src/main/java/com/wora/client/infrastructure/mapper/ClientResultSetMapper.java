package com.wora.client.infrastructure.mapper;

import com.wora.client.domain.entity.Client;
import com.wora.client.domain.valueObject.ClientId;
import com.wora.client.domain.valueObject.Name;
import com.wora.common.infrastructure.mapper.BaseEntityResultSetMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                getDateTime("updated_at", rs),
                getDateTime("deleted_at", rs)
        );
    }

    public void map(final Client client, final PreparedStatement stmt) throws SQLException {
        int count = 1;
        stmt.setString(count++, client.name().firstName());
        stmt.setString(count++, client.name().lastName());
        stmt.setString(count++, client.phone());
        stmt.setString(count++, client.address());
        stmt.setBoolean(count++, client.isProfessional());
        stmt.setObject(count++, client.id().value());
    }
}