package com.wora.component.application.dto.response;

import com.wora.component.domain.enums.ComponentType;
import com.wora.component.domain.valueObject.ComponentId;

import java.time.LocalDateTime;

public class ComponentResponse {
    private final ComponentId id;
    private final String name;
    private final Double tva;
    private final ComponentType type;
    private final String projectId;
    private final Double total;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ComponentResponse(ComponentId id, String name, Double tva, ComponentType type, String projectId, Double total, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.tva = tva;
        this.type = type;
        this.projectId = projectId;
        this.total = total;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ComponentId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public Double tva() {
        return tva;
    }

    public String projectId() {
        return projectId;
    }

    public ComponentType type() {
        return type;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }

    public Double total() {
        return total;
    }
}
