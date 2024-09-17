package com.wora.project.application.service.impl;

import com.wora.client.domain.valueObject.ClientId;
import com.wora.project.application.dto.request.CreateProjectRequest;
import com.wora.project.application.dto.response.ProjectResponse;
import com.wora.project.application.mapper.ProjectMapper;
import com.wora.project.application.service.ProjectService;
import com.wora.project.domain.entity.Project;
import com.wora.project.domain.exception.ProjectNotFoundException;
import com.wora.project.domain.repository.ProjectRepository;
import com.wora.project.domain.valueObject.ProjectId;

import java.util.List;

public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository repository;
    private final ProjectMapper mapper;

    public ProjectServiceImpl(ProjectRepository repository, ProjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<ProjectResponse> findAll() {
        return repository.findAll()
                .stream().map(mapper::map)
                .toList();
    }

    @Override
    public List<ProjectResponse> findByClientId(ClientId clientId) {
        return repository.findByClientId(clientId.value())
                .stream().map(mapper::map)
                .toList();
    }

    @Override
    public ProjectResponse findById(ProjectId id) {
        return repository.findById(id.value())
                .map(mapper::map)
                .orElseThrow(() -> new ProjectNotFoundException(id.value()));
    }

    @Override
    public ProjectResponse create(CreateProjectRequest dto) {
        final Project project = repository.create(mapper.map(dto, new ProjectId()));
        return mapper.map(project);
    }

    @Override
    public ProjectResponse update(ProjectId id, CreateProjectRequest dto) {
        final Project project = repository.update(id.value(), mapper.map(dto, id));
        return mapper.map(project);
    }

    @Override
    public void delete(ProjectId id) {
        repository.delete(id.value());
    }

    @Override
    public boolean existsById(ProjectId id) {
        return repository.existsById(id.value());
    }
}
