package com.wora.client.infrastructure.persistence;

import com.wora.client.domain.entities.Client;
import com.wora.client.domain.exceptions.ClientNotFoundException;
import com.wora.client.domain.repositories.ClientRepository;
import com.wora.client.infrastructure.mappers.ClientResultSetMapper;
import com.wora.common.infrastructure.persistence.BaseRepositoryImpl;

import java.sql.ResultSet;
import java.util.UUID;

import static com.wora.common.utils.QueryExecutor.execute;

public class ClientRepositoryImpl extends BaseRepositoryImpl<Client, UUID> implements ClientRepository {
    public ClientRepositoryImpl(ClientResultSetMapper mapper) {
        super("clients", mapper);
    }

    @Override
    public Client create(Client client) {
        final String query = "INSERT INTO clients (first_name, last_name, phone, address, is_professional, id) VALUES(?,?,?,?,?,?)";
        execute(query, stmt -> mapper.map(client, stmt));

        return findById(client.getId().value())
                .orElseThrow(() -> new ClientNotFoundException(client.getId().value()));
    }

    @Override
    public Client update(UUID uuid, Client client) {
        final String query = """
                UPDATE clients
                SET first_name =?, last_name =?, phone =?, address = ?, is_professional = ?, updated_at = now()
                WHERE id = ?
                """;
        execute(query, stmt -> mapper.map(client, stmt));
        return findById(client.getId().value())
                .orElseThrow(() -> new ClientNotFoundException(client.getId().value()));
    }

    @Override
    public Boolean existsByAddress(String address) {
        return this.existsByColumn("address", address);
    }

    @Override
    public Boolean existsByPhone(String phone) {
        return this.existsByColumn("phone", phone);
    }

}
