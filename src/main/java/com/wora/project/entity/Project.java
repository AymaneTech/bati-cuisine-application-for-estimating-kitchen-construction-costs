package com.wora.project.entity;

import com.wora.client.domain.entity.Client;
import com.wora.common.domain.AbstractEntity;
import com.wora.component.domain.entity.Component;
import com.wora.project.enums.ProjectStatus;
import com.wora.project.valueObject.ProjectId;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Project extends AbstractEntity<ProjectId> {
    private ProjectId id;
    private String name;
    private Double surface;
    private Double totalCost;
    private ProjectStatus projectStatus;
    private Client client;
    private List<Component> components = new ArrayList<>();

    public Project() {
    }

    public Project(ProjectId id, String name, Double surface, Double totalCost, ProjectStatus projectStatus, Client client) {
        this.id = id;
        this.name = name;
        this.surface = surface;
        this.totalCost = totalCost;
        this.projectStatus = projectStatus;
        this.client = client;
    }

    public Project(ProjectId id, String name, Double surface, Double totalCost, ProjectStatus projectStatus, Client client, List<Component> components, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        this(id, name, surface, totalCost, projectStatus, client);
        this.components = components;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public ProjectId id() {
        return id;
    }

    public Project setId(ProjectId id) {
        this.id = id;
        return this;
    }

    public String name() {
        return name;
    }

    public Project setName(String name) {
        this.name = name;
        return this;
    }

    public Double surface() {
        return surface;
    }

    public Project setSufrace(Double surface) {
        this.surface = surface;
        return this;
    }

    public Double totalCost() {
        return totalCost;
    }

    public Project setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
        return this;
    }

    public ProjectStatus projectStatus() {
        return projectStatus;
    }

    public Project setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
        return this;
    }

    public Project setSurface(Double surface) {
        this.surface = surface;
        return this;
    }

    public List<Component> components() {
        return components;
    }

    public Project setComponents(List<Component> components) {
        this.components = components;
        return this;
    }

    public Client client() {
        return client;
    }

    public Project setClient(Client client) {
        this.client = client;
        return this;
    }
}
