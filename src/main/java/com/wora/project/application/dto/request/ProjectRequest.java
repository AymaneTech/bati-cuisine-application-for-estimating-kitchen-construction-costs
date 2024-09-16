package com.wora.project.application.dto.request;

import com.wora.client.domain.valueObject.ClientId;
import com.wora.project.domain.enums.ProjectStatus;

public record ProjectRequest(String name, Double surface, Double totalCost, ProjectStatus projectStatus,
                             ClientId clientId) {
}
