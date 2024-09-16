package com.wora.project.domain.repository;

import com.wora.common.infrastructure.persistence.BaseRepository;
import com.wora.project.application.dto.response.ProjectResponse;
import com.wora.project.domain.entity.Project;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectRepository extends BaseRepository<Project, UUID> {
    Optional<Project> findByName(String name);

    List<Project> findByClientId(UUID clientId);
}
