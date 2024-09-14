package com.wora.client.application.services;

import com.wora.client.application.dtos.requests.CreateClientDto;
import com.wora.client.application.dtos.requests.UpdateClientDto;
import com.wora.client.application.dtos.responses.ClientResponse;
import com.wora.client.domain.entities.Client;
import com.wora.client.domain.valueObjects.ClientId;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<ClientResponse> findAll();

    ClientResponse findById(ClientId id);

    void create(CreateClientDto dto);

    void update(ClientId id, UpdateClientDto dto);

    void delete(ClientId id);

    Boolean existsById(ClientId id);
}
