package com.wora.component.application.dto.request;

import com.wora.component.domain.enums.ComponentType;
import com.wora.project.domain.valueObject.ProjectId;

public class ComponentRequest {
    private final String name;
    private final Double tva;
    private final ComponentType type;
    private final ProjectId projectId;

    public ComponentRequest(String name, Double tva, ComponentType type, ProjectId projectId) {
        this.name = name;
        this.tva = tva;
        this.type = type;
        this.projectId = projectId;
    }

    public String name() {
        return name;
    }

    public ProjectId projectId() {
        return projectId;
    }

    public ComponentType type() {
        return type;
    }

    public Double tva() {
        return tva;
    }
}
