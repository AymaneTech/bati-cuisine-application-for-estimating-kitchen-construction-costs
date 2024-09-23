package com.wora.project.application.mapper;

import com.wora.client.domain.entity.Client;
import com.wora.common.application.mapper.BaseEntityDtoMapper;
import com.wora.project.application.dto.request.CreateProjectRequest;
import com.wora.project.application.dto.request.SaveProjectRequest;
import com.wora.project.application.dto.response.ProjectResponse;
import com.wora.project.domain.entity.Project;
import com.wora.project.domain.valueObject.ProjectId;

public class ProjectMapper implements BaseEntityDtoMapper<Project, CreateProjectRequest, ProjectResponse, ProjectId> {

    @Override
    public Project map(CreateProjectRequest dto, ProjectId id) {
        return new Project(
                id,
                dto.name(),
                dto.surface(),
                dto.projectStatus(),
                new Client().setId(dto.clientId())
        );
    }

    public Project map(SaveProjectRequest dto, ProjectId id) {
        return new Project(
                id,
                dto.name(),
                dto.surface(),
                dto.projectStatus(),
                dto.client()
        )
                .setProfitMargin(dto.profitMargin());
    }

    @Override
    public ProjectResponse map(Project project) {
        return new ProjectResponse(
                project.id(),
                project.name(),
                project.surface(),
                project.projectStatus(),
                project.client(),
                project.profitMargin(),
                project.createdAt(),
                project.updatedAt()
        );
    }
}
