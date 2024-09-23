package com.wora.project.application.service.impl;

import com.wora.component.application.dto.request.MaterielRequest;
import com.wora.component.application.dto.request.WorkerRequest;
import com.wora.component.application.dto.response.MaterielResponse;
import com.wora.component.application.dto.response.WorkerResponse;
import com.wora.component.application.services.ComponentService;
import com.wora.project.application.dto.request.SaveProjectRequest;
import com.wora.project.application.service.ProjectReportService;
import com.wora.project.domain.valueObject.ProjectId;

import java.util.function.Function;

public class ProjectReportServiceImpl implements ProjectReportService {
    private final ComponentService<WorkerRequest, WorkerResponse> workerService;
    private final ComponentService<MaterielRequest, MaterielResponse> materielService;

    public ProjectReportServiceImpl(ComponentService<WorkerRequest, WorkerResponse> workerService, ComponentService<MaterielRequest, MaterielResponse> materielService) {
        this.workerService = workerService;
        this.materielService = materielService;
    }

    @Override
    public Double calculateWithoutProfitMargin(SaveProjectRequest dto) {
        return formatDouble(
                calculateTotal(dto, this::withTvaCalc, this::withoutTvaCalc)
        );
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

    private Double calculateTotal(SaveProjectRequest dto,
                                  Function<ProjectId, Double> withTvaCalc,
                                  Function<ProjectId, Double> withoutTvaCalc) {
        return dto.applyTva() ? withTvaCalc.apply(dto.id())
                : withoutTvaCalc.apply(dto.id());
    }

    private Double withTvaCalc(ProjectId projectId) {
        return materielService.calculateTotalCostWithTva(materielService.findAllByProjectId(projectId))
                + workerService.calculateTotalCostWithTva(workerService.findAllByProjectId(projectId));
    }

    private Double withoutTvaCalc(ProjectId projectId) {
        return materielService.calculateTotalCost(materielService.findAllByProjectId(projectId))
                + workerService.calculateTotalCost(workerService.findAllByProjectId(projectId));
    }

    private Double formatDouble(Double d) {
        return Double.valueOf(String.format("%.2f", d));
    }
}
