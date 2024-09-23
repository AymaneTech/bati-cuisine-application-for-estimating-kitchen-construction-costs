package com.wora.project.application.dto.request;

import com.wora.client.domain.entity.Client;
import com.wora.project.domain.enums.ProjectStatus;
import com.wora.project.domain.valueObject.ProjectId;

public record SaveProjectRequest(ProjectId id, String name, Double surface, ProjectStatus projectStatus,
                                 Client client, Boolean applyTva, Double profitMargin) {
}
