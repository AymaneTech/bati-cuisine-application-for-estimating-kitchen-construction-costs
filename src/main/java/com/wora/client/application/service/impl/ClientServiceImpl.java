package com.wora.client.application.service.impl;

import com.wora.client.application.dto.request.ClientRequest;
import com.wora.client.application.dto.response.ClientResponse;
import com.wora.client.application.mapper.ClientMapper;
import com.wora.client.application.service.ClientService;
import com.wora.client.domain.entity.Client;
import com.wora.client.domain.exception.ClientNotFoundException;
import com.wora.client.domain.repository.ClientRepository;
import com.wora.client.domain.valueObject.ClientId;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    private final ClientRepository repository;
    private final ClientMapper mapper;

    public ClientServiceImpl(ClientRepository repository, ClientMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<ClientResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::map)
                .toList();
    }

    @Override
    public ClientResponse findById(ClientId id) {
        return repository.findById(id.value())
                .map(mapper::map)
                .orElseThrow(() -> new ClientNotFoundException(id.value()));
    }

    @Override
    public ClientResponse create(ClientRequest dto) {
        final Client client = repository.create(mapper.map(dto, new ClientId()));
        return mapper.map(client);
    }

    @Override
    public ClientResponse update(ClientId id, ClientRequest dto) {
        final Client client = repository.update(id.value(), mapper.map(dto, id));
        return mapper.map(client);
    }

    @Override
    public void delete(ClientId id) {
        repository.delete(id.value());
    }

    @Override
    public Boolean existsById(ClientId id) {
        return repository.existsById(id.value());
    }

    @Override
    public Boolean existsByAddress(String address) {
        return repository.existsByAddress(address);
    }

    @Override
    public Boolean existsByPhone(String phone) {
        return repository.existsByPhone(phone);
    }
}
