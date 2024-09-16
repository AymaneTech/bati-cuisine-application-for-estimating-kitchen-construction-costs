package com.wora.project.application.mapper;

import com.wora.client.domain.entity.Client;
import com.wora.common.application.mapper.BaseEntityDtoMapper;
import com.wora.project.application.dto.request.ProjectRequest;
import com.wora.project.application.dto.response.ProjectResponse;
import com.wora.project.domain.entity.Project;
import com.wora.project.domain.valueObject.ProjectId;

public class ProjectMapper implements BaseEntityDtoMapper<Project, ProjectRequest, ProjectResponse, ProjectId> {

    @Override
    public Project map(ProjectRequest dto, ProjectId id) {
        return new Project(
                id,
                dto.name(),
                dto.surface(),
                dto.totalCost(),
                dto.projectStatus(),
                new Client().setId(dto.clientId())
        );
    }

    @Override
    public ProjectResponse map(Project project) {
        return new ProjectResponse(
                project.id(),
                project.name(),
                project.surface(),
                project.totalCost(),
                project.projectStatus(),
                project.client().id(),
                project.createdAt(),
                project.updatedAt()
        );
    }
}
