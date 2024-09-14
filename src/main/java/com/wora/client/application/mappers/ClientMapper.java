package com.wora.client.application.mappers;

import com.wora.client.application.dtos.requests.CreateClientDto;
import com.wora.client.application.dtos.requests.UpdateClientDto;
import com.wora.client.application.dtos.responses.ClientResponse;
import com.wora.client.domain.entities.Client;
import com.wora.client.domain.valueObjects.ClientId;

public class ClientMapper {

    public Client map(CreateClientDto dto) {
        return new Client(
                new ClientId(),
                dto.name(),
                dto.email(),
                dto.phone()
        );
    }

    public Client map(UpdateClientDto dto, ClientId id) {
        return new Client(
                id,
                dto.name(),
                dto.email(),
                dto.phone()
        );
    }

    public ClientResponse map(Client client) {
        return new ClientResponse(
                client.getName(),
                client.getEmail(),
                client.getPhone()
        );
    }
}