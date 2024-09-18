package com.wora.component.application.dto.response;

import com.wora.component.domain.enums.ComponentType;
import com.wora.component.domain.valueObject.ComponentId;

import java.time.LocalDateTime;

public class WorkerResponse extends ComponentResponse {
    private final Double hourlyRate;
    private final Double workingHours;
    private final Double productivity;

    public WorkerResponse(ComponentId id, String name, Double tva, String projectId, Double hourlyRate, Double workingHours, Double productivity, Double total, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, name, tva, ComponentType.WORKER, projectId, total, createdAt, updatedAt);
        this.hourlyRate = hourlyRate;
        this.workingHours = workingHours;
        this.productivity = productivity;
    }

    public Double hourlyRate() {
        return hourlyRate;
    }

    public Double workingHours() {
        return workingHours;
    }

    public Double productivity() {
        return productivity;
    }
}
