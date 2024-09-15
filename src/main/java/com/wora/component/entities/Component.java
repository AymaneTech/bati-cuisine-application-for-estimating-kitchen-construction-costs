package com.wora.component.entities;

import com.wora.common.domain.AbstractEntity;
import com.wora.component.enums.ComponentType;
import com.wora.component.valueObjects.ComponentId;

import java.time.LocalDateTime;

public class Component extends AbstractEntity<ComponentId> {
    private ComponentId id;
    private String name;
    private Double tva;
    private ComponentType componentType;

    public Component(ComponentId id, String name, Double tva, ComponentType componentType) {
        this.id = id;
        this.name = name;
        this.tva = tva;
        this.componentType = componentType;
    }


    public Component(ComponentId id, String name, Double tva, ComponentType componentType, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        this(id, name, tva, componentType);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    @Override
    public ComponentId id() {
        return id;
    }

    public Component setId(ComponentId id) {
        this.id = id;
        return this;
    }

    public String name() {
        return name;
    }

    public Component setName(String name) {
        this.name = name;
        return this;
    }

    public Double tva() {
        return tva;
    }

    public Component setTva(Double tva) {
        this.tva = tva;
        return this;
    }

    public ComponentType componentType() {
        return componentType;
    }

    public Component setComponentType(ComponentType componentType) {
        this.componentType = componentType;
        return this;
    }
}