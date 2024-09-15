package com.wora.project.entities;

import com.wora.common.domain.AbstractEntity;
import com.wora.project.enums.ProjectStatus;
import com.wora.project.valueObjects.ProjectId;

import java.time.LocalDateTime;

public class Project extends AbstractEntity<ProjectId> {
    private ProjectId id;
    private String name;
    private Double surface;
    private Double totalCost;
    private ProjectStatus projectStatus;

    public Project(ProjectId id, String name, Double surface, Double totalCost, ProjectStatus projectStatus) {
        this.id = id;
        this.name = name;
        this.surface = surface;
        this.totalCost = totalCost;
        this.projectStatus = projectStatus;
    }

    public Project(ProjectId id, String name, Double surface, Double totalCost, ProjectStatus projectStatus, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        this(id, name, surface, totalCost, projectStatus);
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
}
