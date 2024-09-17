package com.wora.project.application.dto.request;

import com.wora.client.domain.valueObject.ClientId;
import com.wora.project.domain.enums.ProjectStatus;

public record CreateProjectRequest(String name, Double surface, ProjectStatus projectStatus,
                                   ClientId clientId) {
}
