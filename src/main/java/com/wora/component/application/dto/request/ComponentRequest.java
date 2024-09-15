package com.wora.component.application.dto.request;

import com.wora.component.domain.enums.ComponentType;

public class ComponentRequest {
    private final String name;
    private final String tva;
    private final ComponentType type;
    private final String projectId;

    public ComponentRequest(String name, String tva, ComponentType type, String projectId) {
        this.name = name;
        this.tva = tva;
        this.type = type;
        this.projectId = projectId;
    }

    public String name() {
        return name;
    }

    public String tva() {
        return tva;
    }

    public String projectId() {
        return projectId;
    }

    public ComponentType type() {
        return type;
    }
}
