package com.wora.client.application.mapper;

import com.wora.client.application.dto.request.ClientRequest;
import com.wora.client.application.dto.response.ClientResponse;
import com.wora.client.domain.entity.Client;
import com.wora.client.domain.valueObject.ClientId;

import java.util.List;

public class ClientMapper {

    public Client map(ClientRequest dto, ClientId id) {
        return new Client(
                id,
                dto.name(),
                dto.phone(),
                dto.address(),
                dto.isProfessional(),
                List.of()
        );
    }

    public ClientResponse map(Client client) {
        return new ClientResponse(
                client.id(),
                client.name(),
                client.phone(),
                client.address(),
                client.isProfessional(),
                client.createdAt(),
                client.updatedAt()
        );
    }
}