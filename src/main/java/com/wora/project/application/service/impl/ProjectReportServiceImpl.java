package com.wora.project.application.service.impl;

import com.wora.component.application.dto.request.MaterielRequest;
import com.wora.component.application.dto.request.WorkerRequest;
import com.wora.component.application.dto.response.MaterielResponse;
import com.wora.component.application.dto.response.WorkerResponse;
import com.wora.component.application.services.ComponentService;
import com.wora.project.application.dto.request.SaveProjectRequest;
import com.wora.project.application.service.ProjectReportService;

public class ProjectReportServiceImpl implements ProjectReportService {
    private final ComponentService<WorkerRequest, WorkerResponse> workerService;
    private final ComponentService<MaterielRequest, MaterielResponse> materielService;

    public ProjectReportServiceImpl(ComponentService<WorkerRequest, WorkerResponse> workerService, ComponentService<MaterielRequest, MaterielResponse> materielService) {
        this.workerService = workerService;
        this.materielService = materielService;
    }

    @Override
    public Double calculateWithoutProfitMargin(SaveProjectRequest dto) {
        final Double materielCost = materielService.calculateTotalCostWithTva(materielService.findAllByProjectId(dto.id()));
        final Double workerCost = workerService.calculateTotalCostWithTva(workerService.findAllByProjectId(dto.id()));

        return materielCost + workerCost;
    }

    @Override
    public Double calculateProfitMargin(SaveProjectRequest dto) {
        Double totalCost = calculateWithoutProfitMargin(dto);
        return (totalCost * dto.profitMargin()) / 100;
    }

    @Override
    public Double calculateWithProfitMargin(SaveProjectRequest dto) {
        final Double totalCostWithoutMargin = calculateWithoutProfitMargin(dto);
        final Double profitMarginAmount = calculateProfitMargin(dto);
        return totalCostWithoutMargin + profitMarginAmount;
    }
}
