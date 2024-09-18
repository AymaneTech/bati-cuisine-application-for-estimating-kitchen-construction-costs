package com.wora.project.infrastructure.presentation;

import com.wora.client.domain.valueObject.ClientId;
import com.wora.client.infrastructure.presentation.ClientUi;
import com.wora.common.util.ValidationStrategies;
import com.wora.component.infrastructure.presentation.MaterielUi;
import com.wora.component.infrastructure.presentation.WorkerUi;
import com.wora.project.application.dto.request.CreateProjectRequest;
import com.wora.project.application.dto.request.SaveProjectRequest;
import com.wora.project.application.dto.response.ProjectResponse;
import com.wora.project.application.service.ProjectReportService;
import com.wora.project.application.service.ProjectService;
import com.wora.project.domain.enums.ProjectStatus;

import static com.wora.common.util.InputScanner.*;

public class ProjectUi {
    private final ProjectService service;
    private final ProjectReportService costCalculatingService;
    private final ClientUi clientUi;
    private final MaterielUi materielUi;
    private final WorkerUi workerUi;

    public ProjectUi(ProjectService service, ProjectReportService costCalculatingService, ClientUi clientUi, MaterielUi materielUi, WorkerUi workerUi) {
        this.service = service;
        this.costCalculatingService = costCalculatingService;
        this.clientUi = clientUi;
        this.materielUi = materielUi;
        this.workerUi = workerUi;
    }

    public void create() {
        final ClientId clientId = clientUi.searchOrCreate();
        final String name = scanString("Please enter the project name: ", ValidationStrategies.NOT_BLANK);
        final Double surface = scanDouble("Please to enter the surface of " + name + " in m²: ", ValidationStrategies.POSITIVE_DOUBLE);
        final ProjectStatus projectStatus = scanEnum("Please enter the number of project status: ", ProjectStatus.class);

        final ProjectResponse createdProject = service.create(new CreateProjectRequest(name, surface, projectStatus, clientId));

        executeIfUserConfirms("Do you want to add new materiels",
                () -> materielUi.create(createdProject.id())
        );

        executeIfUserConfirms("Do you want to add new workers",
                () -> workerUi.create(createdProject.id())
        );

        final Double tva = executeIfUserConfirmsWithResult("Do you want to apply TVA ",
                () -> scanDouble("Please to enter the TVA (%): ", ValidationStrategies.POSITIVE_DOUBLE),
                0.0
        );

        final Double profitMargin = executeIfUserConfirmsWithResult("Do you want to apply profit Margin ",
                () -> scanDouble("Please to enter profit margin (%): ", ValidationStrategies.POSITIVE_DOUBLE),
                1.0
        );
        SaveProjectRequest saveProject = new SaveProjectRequest(
                createdProject.id(), createdProject.name(),
                createdProject.surface(), createdProject.totalCost(),
                createdProject.projectStatus(), createdProject.client(),
                tva, profitMargin
        );

        printReport(saveProject);
    }

    public void printReport(SaveProjectRequest project) {
        clearScreen();
        System.out.println("Report generating ...");
        System.out.println("--------------------------------------------------------");
        System.out.println("Project Name: " + project.name());
        System.out.println("Client name : " + project.client().name().fullName());
        System.out.println("Client address: " + project.client().address());
        System.out.println("Surface (m²): " + project.surface());
        System.out.println("--------------------------------------------------------");
        System.out.println("Cost Details");

        System.out.println("1. Materiels: ");
        materielUi.showByProjectId(project.id());
        materielUi.showTotalCostOfMateriels(project.id());

        System.out.println("2. Workers: ");
        workerUi.showByProjectId(project.id());
        workerUi.showTotalCostOfWorkers(project.id());

        System.out.println("Total cost without margin: " + costCalculatingService.calculateWithoutProfitMargin(project));
        System.out.println("Profit Margin: " + costCalculatingService.calculateProfitMargin(project));
        System.out.println("Total cost with margin: " + costCalculatingService.calculateWithProfitMargin(project));
    }
}
