package com.wora.client.application.service;

import com.wora.client.application.dto.request.ClientRequest;
import com.wora.client.application.dto.response.ClientResponse;
import com.wora.client.domain.valueObject.ClientId;

import java.util.List;

public interface ClientService {
    List<ClientResponse> findAll();

    ClientResponse findById(ClientId id);

    ClientResponse create(ClientRequest dto);

    ClientResponse update(ClientId id, ClientRequest dto);

    void delete(ClientId id);

    Boolean existsById(ClientId id);

    Boolean existsByAddress(String address);

    Boolean existsByPhone(String phone);
}
