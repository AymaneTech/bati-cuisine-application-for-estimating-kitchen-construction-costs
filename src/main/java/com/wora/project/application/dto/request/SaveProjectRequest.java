package com.wora.project.application.dto.request;

import com.wora.client.domain.valueObject.ClientId;
import com.wora.project.domain.enums.ProjectStatus;
import com.wora.project.domain.valueObject.ProjectId;

import java.time.LocalDateTime;

public record SaveProjectRequest(ProjectId id, String name, Double surface, Double totalCost, ProjectStatus projectStatus,
                                 ClientId clientId, Double tva, Double profitMargin) {
}
