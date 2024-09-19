package com.wora.project.application.dto.response;

import com.wora.client.domain.entity.Client;
import com.wora.project.domain.enums.ProjectStatus;
import com.wora.project.domain.valueObject.ProjectId;

import java.time.LocalDateTime;

public record ProjectResponse(ProjectId id, String name, Double surface, Double totalCost, ProjectStatus projectStatus,
                              Client client, Double tva, Double profitMargin, LocalDateTime createdAt,
                              LocalDateTime updatedAt) {
}
