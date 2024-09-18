package com.wora.component.domain.entity;

import com.wora.common.domain.AbstractEntity;
import com.wora.component.domain.enums.ComponentType;
import com.wora.component.domain.valueObject.ComponentId;
import com.wora.project.domain.entity.Project;

import java.time.LocalDateTime;

public abstract class Component extends AbstractEntity<ComponentId> {
    protected ComponentId id;
    protected String name;
    protected Double tva;
    protected ComponentType componentType;
    protected Project project;

    public Component() {
    }

    public Component(ComponentId id, String name, Double tva, ComponentType componentType, Project project) {
        this.id = id;
        this.name = name;
        this.tva = tva;
        this.componentType = componentType;
        this.project = project;
    }


    public Component(ComponentId id, String name, Double tva, ComponentType componentType, Project project, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        this(id, name, tva, componentType, project);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public abstract Double total();

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

    public Project project() {
        return project;
    }

    public Component setProject(Project project) {
        this.project = project;
        return this;
    }
}
