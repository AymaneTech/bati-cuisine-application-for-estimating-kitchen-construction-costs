package com.wora.client.infrastructure.persistence;

import com.wora.client.domain.entities.Client;
import com.wora.client.domain.repositories.ClientRepository;
import com.wora.client.infrastructure.mappers.ClientResultSetMapper;
import com.wora.common.infrastructure.persistence.BaseRepositoryImpl;

import java.util.UUID;

import static com.wora.common.utils.QueryExecutor.executeQueryPreparedStatement;
import static com.wora.common.utils.QueryExecutor.executeUpdatePreparedStatement;

public class ClientRepositoryImpl extends BaseRepositoryImpl<Client, UUID> implements ClientRepository {
    public ClientRepositoryImpl(ClientResultSetMapper mapper) {
        super("clients", mapper);
    }

    @Override
    public void create(Client client) {
        final String query = "INSERT INTO clients (first_name, last_name, phone, address, is_professional, id) VALUES(?,?,?,?,?,?)";
        executeUpdatePreparedStatement(query, stmt -> mapper.map(client, stmt));
    }

    @Override
    public void update(UUID uuid, Client client) {
        final String query = """
                UPDATE clients
                SET first_name =?, last_name =?, phone =?, address = ?, is_professional = ?, updated_at = now()
                WHERE id = ?
                """;
        executeQueryPreparedStatement(query, stmt -> mapper.map(client, stmt));
    }
}
