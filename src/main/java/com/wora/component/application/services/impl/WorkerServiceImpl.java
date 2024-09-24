package com.wora.component.application.services.impl;

import java.util.List;

import com.wora.component.application.dto.request.WorkerRequest;
import com.wora.component.application.dto.response.WorkerResponse;
import com.wora.component.application.mapper.WorkerMapper;
import com.wora.component.application.services.ComponentService;
import com.wora.component.domain.entity.Worker;
import com.wora.component.domain.enums.ComponentType;
import com.wora.component.domain.exception.ComponentNotFoundException;
import com.wora.component.domain.repository.ComponentRepository;
import com.wora.component.domain.valueObject.ComponentId;
import com.wora.project.domain.valueObject.ProjectId;

public class WorkerServiceImpl implements ComponentService<WorkerRequest, WorkerResponse> {
    private final ComponentRepository<Worker> repository;
    private final WorkerMapper mapper;

    public WorkerServiceImpl(ComponentRepository<Worker> repository, WorkerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<WorkerResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::map)
                .toList();
    }

    @Override
    public List<WorkerResponse> findAllByProjectId(ProjectId projectId) {
        return repository.findAllByProjectId(projectId.value())
                .stream().map(mapper::map)
                .toList();
    }

    @Override
    public WorkerResponse findById(ComponentId id) {
        return repository.findById(id.value())
                .map(mapper::map)
                .orElseThrow(() -> new ComponentNotFoundException(id.value(), ComponentType.WORKER));
    }

    @Override
    public WorkerResponse create(WorkerRequest dto) {
        final Worker worker = repository.create(mapper.map(dto, new ComponentId()));
        return mapper.map(worker);
    }

    @Override
    public WorkerResponse update(ComponentId id, WorkerRequest dto) {
        final Worker worker = repository.update(id.value(), mapper.map(dto, id));
        return mapper.map(worker);
    }

    @Override
    public void delete(ComponentId id) {
        repository.delete(id.value());
    }

    @Override
    public boolean existsById(ComponentId id) {
        return repository.existsById(id.value());
    }
}
