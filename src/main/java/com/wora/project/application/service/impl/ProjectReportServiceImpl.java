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
        final Double totalCostWithoutTva = materielCost + workerCost;

        if (dto.tva() > 0) {
            return formatDouble(totalCostWithoutTva * (1 + dto.tva() / 100));
        }
        return formatDouble(totalCostWithoutTva);
    }

    @Override
    public Double calculateProfitMargin(SaveProjectRequest dto) {
        Double totalCost = calculateWithoutProfitMargin(dto);
        return formatDouble((totalCost * dto.profitMargin()) / 100);
    }

    @Override
    public Double calculateWithProfitMargin(SaveProjectRequest dto) {
        final Double totalCostWithoutMargin = calculateWithoutProfitMargin(dto);
        final Double profitMarginAmount = calculateProfitMargin(dto);
        return formatDouble(totalCostWithoutMargin + profitMarginAmount);
    }

    private Double formatDouble(Double d) {
        return Double.valueOf(String.format("%.2f", d));
    }
}
