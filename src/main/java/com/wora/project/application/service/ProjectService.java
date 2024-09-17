package com.wora.project.application.service;

import com.wora.client.domain.valueObject.ClientId;
import com.wora.project.application.dto.request.CreateProjectRequest;
import com.wora.project.application.dto.response.ProjectResponse;
import com.wora.project.domain.valueObject.ProjectId;

import java.util.List;

public interface ProjectService {
    List<ProjectResponse> findAll();

    List<ProjectResponse> findByClientId(ClientId clientId);

    ProjectResponse findById(ProjectId id);

    ProjectResponse create(CreateProjectRequest dto);

    ProjectResponse update(ProjectId id, CreateProjectRequest dto);

    void delete(ProjectId id);

    boolean existsById(ProjectId id);
}
