package com.wora.project.application.dto.request;

import com.wora.client.domain.entity.Client;
import com.wora.client.domain.valueObject.ClientId;
import com.wora.client.domain.valueObject.Name;
import com.wora.project.domain.enums.ProjectStatus;
import com.wora.project.domain.valueObject.ProjectId;

import java.time.LocalDateTime;

public record SaveProjectRequest(ProjectId id, String name, Double surface, Double totalCost, ProjectStatus projectStatus,
                                 Client client, Double tva, Double profitMargin) {
}
