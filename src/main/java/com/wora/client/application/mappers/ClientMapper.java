package com.wora.client.application.mappers;

import com.wora.client.application.dtos.requests.ClientRequest;
import com.wora.client.application.dtos.responses.ClientResponse;
import com.wora.client.domain.entities.Client;
import com.wora.client.domain.valueObjects.ClientId;

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