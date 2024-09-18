package com.wora.project.application.service.impl;

import com.wora.component.application.dto.request.MaterielRequest;
import com.wora.component.application.dto.request.WorkerRequest;
import com.wora.component.application.dto.response.MaterielResponse;
import com.wora.component.application.dto.response.WorkerResponse;
import com.wora.component.application.services.ComponentService;
import com.wora.project.application.dto.request.SaveProjectRequest;
import com.wora.project.application.service.ProjectCostCalculatingService;
import com.wora.project.application.service.ProjectService;

public class ProjectCostCalculatingServiceImpl implements ProjectCostCalculatingService {
    private final ComponentService<WorkerRequest, WorkerResponse> workerService;
    private final ComponentService<MaterielRequest, MaterielResponse> materielService;
    private final ProjectService projectService;

    public ProjectCostCalculatingServiceImpl(ComponentService<WorkerRequest, WorkerResponse> workerService, ComponentService<MaterielRequest, MaterielResponse> materielService, ProjectService projectService) {
        this.workerService = workerService;
        this.materielService = materielService;
        this.projectService = projectService;
    }

    @Override
    public void calculate(SaveProjectRequest dto) {

    }
}
