package com.wora.component.application.dto.request;

import com.wora.component.domain.enums.ComponentType;

public class WorkerRequest extends ComponentRequest{
    private final Double hourlyRate;
    private final Double workingHours;
    private final Double productivity;

    public WorkerRequest(String name, String tva, String projectId, Double hourlyRate, Double workingHours, Double productivity) {
        super(name, tva, ComponentType.WORKER, projectId);
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
