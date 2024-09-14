package com.wora.client.application.mappers;

import com.wora.client.application.dtos.requests.ClientRequest;
import com.wora.client.application.dtos.responses.ClientResponse;
import com.wora.client.domain.entities.Client;
import com.wora.client.domain.valueObjects.ClientId;

public class ClientMapper {

    public Client map(ClientRequest dto) {
        return new Client(
                new ClientId(),
                dto.name(),
                dto.phone(),
                dto.address(),
                dto.isProfessional()
        );
    }

    public ClientResponse map(Client client) {
        return new ClientResponse(
                client.getName(),
                client.getPhone(),
                client.getAddress(),
                client.getIsProfessional()
        );
    }
}