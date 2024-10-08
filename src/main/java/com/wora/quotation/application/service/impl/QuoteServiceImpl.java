package com.wora.quotation.application.service.impl;

import com.wora.client.domain.valueObject.ClientId;
import com.wora.project.domain.valueObject.ProjectId;
import com.wora.quotation.application.dto.request.QuoteRequest;
import com.wora.quotation.application.dto.response.QuoteResponse;
import com.wora.quotation.application.mapper.QuoteMapper;
import com.wora.quotation.application.service.QuoteService;
import com.wora.quotation.domain.Quote;
import com.wora.quotation.domain.exception.QuoteNotFoundException;
import com.wora.quotation.domain.repository.QuoteRepository;
import com.wora.quotation.domain.valueObject.QuoteId;

import java.util.List;

public class QuoteServiceImpl implements QuoteService {
    private final QuoteRepository repository;
    private final QuoteMapper mapper;

    public QuoteServiceImpl(QuoteRepository repository, QuoteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<QuoteResponse> findAll() {
        return repository.findAll()
                .stream().map(mapper::map)
                .toList();
    }

    @Override
    public List<QuoteResponse> findByClient(ClientId clientId) {
        return repository.findAllByClientId(clientId.value())
                .stream().map(mapper::map)
                .toList();
    }

    @Override
    public List<QuoteResponse> findByProject(ProjectId projectId) {
        return repository.findAllByProjectId(projectId.value())
                .stream().map(mapper::map)
                .toList();
    }

    @Override
    public QuoteResponse findById(QuoteId id) {
        return repository.findById(id.value())
                .map(mapper::map)
                .orElseThrow(() -> new QuoteNotFoundException(id.value()));
    }

    @Override
    public QuoteResponse create(QuoteRequest dto) {
        final Quote quote = repository.create(mapper.map(dto, new QuoteId()));
        return mapper.map(quote);
    }

    @Override
    public QuoteResponse update(QuoteId id, QuoteRequest dto) {
        final Quote quote = repository.update(id.value(), mapper.map(dto, id));
        return mapper.map(quote);
    }

    @Override
    public void delete(QuoteId id) {
        repository.delete(id.value());
    }

    @Override
    public boolean existsById(QuoteId id) {
        return repository.existsById(id.value());
    }

}
