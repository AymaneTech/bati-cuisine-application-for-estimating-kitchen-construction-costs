package com.wora.client.application.services;

import com.wora.client.application.dtos.requests.ClientRequest;
import com.wora.client.application.dtos.responses.ClientResponse;
import com.wora.client.domain.valueObjects.ClientId;

import java.util.List;

public interface ClientService {
    List<ClientResponse> findAll();

    ClientResponse findById(ClientId id);

    ClientResponse create(ClientRequest dto);

    void update(ClientId id, ClientRequest dto);

    void delete(ClientId id);

    Boolean existsById(ClientId id);
}
