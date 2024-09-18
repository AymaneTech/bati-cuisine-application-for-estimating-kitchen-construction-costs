package com.wora.component.domain.entity;

import com.wora.component.domain.enums.ComponentType;
import com.wora.component.domain.valueObject.ComponentId;
import com.wora.project.domain.entity.Project;

import java.time.LocalDateTime;

public class Worker extends Component {
    private Double hourlyRate;
    private Double workingHours;
    private Double productivity;

    public Worker(ComponentId id, String name, Double tva, Project project, Double hourlyRate, Double workingHours, Double productivity) {
        super(id, name, tva, ComponentType.WORKER, project);
        this.hourlyRate = hourlyRate;
        this.workingHours = workingHours;
        this.productivity = productivity;
    }

    public Worker(ComponentId id, String name, Double tva, Project project, Double hourlyRate, Double workingHours, Double productivity, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        super(id, name, tva, ComponentType.WORKER, project, createdAt, updatedAt, deletedAt);
        this.hourlyRate = hourlyRate;
        this.workingHours = workingHours;
        this.productivity = productivity;
    }

    public Double hourlyRate() {
        return hourlyRate;
    }

    public Worker setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
        return this;
    }

    public Double workingHours() {
        return workingHours;
    }

    public Worker setWorkingHours(Double workingHours) {
        this.workingHours = workingHours;
        return this;
    }

    public Double productivity() {
        return productivity;
    }

    public Worker setProductivity(Double productivity) {
        this.productivity = productivity;
        return this;
    }

    @Override
    public Double total() {
        return hourlyRate * workingHours * productivity;
    }
}
