package com.wora.project.infrastructure.presentation;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.wora.client.domain.valueObject.ClientId;
import com.wora.client.infrastructure.presentation.ClientUi;
import com.wora.common.util.ValidationStrategies;
import com.wora.component.infrastructure.presentation.MaterielUi;
import com.wora.component.infrastructure.presentation.WorkerUi;
import com.wora.mainMenu.MainMenuUi;
import com.wora.project.application.dto.request.CreateProjectRequest;
import com.wora.project.application.dto.request.SaveProjectRequest;
import com.wora.project.application.dto.response.ProjectResponse;
import com.wora.project.application.service.ProjectCostCalculatingService;
import com.wora.project.application.service.ProjectService;
import com.wora.project.domain.enums.ProjectStatus;
import com.wora.project.domain.valueObject.ProjectId;
import com.wora.quotation.infrastructure.presentation.QuoteUi;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.wora.common.util.InputScanner.*;
import static com.wora.common.util.Print.title;

public class ProjectUi {
    private final ProjectService service;
    private final ProjectCostCalculatingService costCalculatingService;
    private final ClientUi clientUi;
    private final MaterielUi materielUi;
    private final WorkerUi workerUi;
    private final QuoteUi quoteUi;
    private MainMenuUi menuUi;

    public ProjectUi(ProjectService service, ProjectCostCalculatingService costCalculatingService, ClientUi clientUi, MaterielUi materielUi, WorkerUi workerUi, QuoteUi quoteUi) {
        this.service = service;
        this.costCalculatingService = costCalculatingService;
        this.clientUi = clientUi;
        this.materielUi = materielUi;
        this.workerUi = workerUi;
        this.quoteUi = quoteUi;
    }

    public void showMenu() {
        title("Welcome to project menu");
        System.out.println("1. Create new project");
        System.out.println("2. Update an existing project");
        System.out.println("3. Delete an existing project");
        System.out.println("4. Show all projects");
        System.out.println("5. Show project by id");
        System.out.println("6. Back to main menu.");
        System.out.println("0. exit.");

        Integer userChoice = scanInt("Please to enter you choice: ", ValidationStrategies.POSITIVE_INT);

        switch (userChoice) {
            case 1 -> this.create();
            case 2 -> this.update();
            case 3 -> this.delete();
            case 4 -> this.showAll();
            case 5 -> this.showById();
            case 6 -> menuUi.showMenu();
            case 0 -> System.exit(0);
            default -> throw new IllegalArgumentException("Invalid choice");
        }
    }

    public void create() {
        final ClientId clientId = clientUi.searchOrCreate();
        final String name = scanString("Please enter the project name: ", ValidationStrategies.combine(
                ValidationStrategies.NOT_BLANK,
                input -> !service.existsByName(input)
        ));
        final Double surface = scanDouble("Please to enter the surface of " + name + " in m²: ", ValidationStrategies.POSITIVE_DOUBLE);
        final ProjectStatus projectStatus = scanEnum("Please enter the number of project status: ", ProjectStatus.class);

        final ProjectResponse createdProject = service.create(new CreateProjectRequest(name, surface, projectStatus, clientId));

        executeIfUserConfirms("Do you want to add new materiels",
                () -> materielUi.create(createdProject.id())
        );

        executeIfUserConfirms("Do you want to add new workers",
                () -> workerUi.create(createdProject.id())
        );

        final Boolean applyTva = scanBoolean("Do you want to apply TVA (y/n): ");

        final Double profitMargin = executeIfUserConfirmsWithResult("Do you want to apply profit Margin ",
                () -> scanDouble("Please to enter profit margin (%): ", ValidationStrategies.POSITIVE_DOUBLE),
                1.0
        );
        
        final SaveProjectRequest saveProject = new SaveProjectRequest(
                createdProject.id(), createdProject.name(),
                createdProject.surface(),
                createdProject.projectStatus(), createdProject.client(),
                applyTva, profitMargin
        );

        service.update(saveProject.id(), saveProject);
        printReport(saveProject);
        executeIfUserConfirms("Do you want to save the quote of this proejct: ", () -> quoteUi.create(saveProject.id()));
        this.showMenu();
    }

    public void update() {
        final UUID id = scanUuid("Please enter the project ID you want to update: ", ValidationStrategies.combine(
                input -> service.existsById(new ProjectId(input))
        ));

        ProjectResponse existingProject = service.findById(new ProjectId(id));

        System.out.println("Existing project details:");
        printReport(existingProject);

        String name = executeIfUserConfirmsWithResult("Do you want to change the project name? (Current: " + existingProject.name() + ")",
                () -> scanString("Please enter the new project name: ", ValidationStrategies.combine(
                        ValidationStrategies.NOT_BLANK,
                        input -> !service.existsByName(input)
                )), existingProject.name()
        );

        Double surface = executeIfUserConfirmsWithResult("Do you want to change the surface? (Current: " + existingProject.surface() + "m²)",
                () -> scanDouble("Please enter the new surface in m²: ", ValidationStrategies.POSITIVE_DOUBLE),
                existingProject.surface()
        );

        ProjectStatus projectStatus = executeIfUserConfirmsWithResult("Do you want to change the project status? (Current: " + existingProject.projectStatus() + ")",
                () -> scanEnum("Please enter the new project status: ", ProjectStatus.class),
                existingProject.projectStatus()
        );

        final Double profitMargin = executeIfUserConfirmsWithResult("Do you want to change the profit margin? (Current: " + existingProject.profitMargin() + "%)",
                () -> scanDouble("Please enter the new profit margin (%): ", ValidationStrategies.POSITIVE_DOUBLE),
                existingProject.profitMargin()
        );

        SaveProjectRequest updatedProject = new SaveProjectRequest(
                existingProject.id(),
                name,
                surface,
                projectStatus,
                existingProject.client(),
                false,
                profitMargin
        );

        service.update(updatedProject.id(), updatedProject);

        System.out.println("Project updated successfully!");
        printReport(updatedProject);
        this.showMenu();
    }


    public void showAll() {
        System.out.println(getTable(service.findAll()));
        this.showMenu();
    }

    public void showById() {
        final UUID id = scanUuid("Please to enter the project id: ", ValidationStrategies.combine(
                input -> service.existsById(new ProjectId(input))
        ));

        printReport(service.findById(new ProjectId(id)));
        this.showMenu();
    }

    public void delete() {
        final String projectName = scanString("Please to enter the name of project you wish to delete", ValidationStrategies.combine(
                ValidationStrategies.NOT_BLANK,
                service::existsByName
        ));
        service.deleteByName(projectName);
        System.out.println(" project deleted successfully!");
        this.showMenu();
    }

    public void printReport(ProjectResponse project) {
        printReport(new SaveProjectRequest(project.id(), project.name(), project.surface(), project.projectStatus(), project.client(), false, project.profitMargin()));
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

    public void setMenuUi(MainMenuUi menuUi) {
        this.menuUi = menuUi;
    }

    private String getTable(List<ProjectResponse> projects) {
        return AsciiTable.getTable(projects, Arrays.asList(
                new Column().header("Project ID").with(p -> p.id().value().toString()),
                new Column().header("Project Name").with(ProjectResponse::name),
                new Column().header("Surface").with(p -> String.format("%.2f", p.surface())),
                new Column().header("Project Status").with(p -> p.projectStatus().name()),
                new Column().header("Client Name").with(p -> p.client().name().fullName()),
                new Column().header("Client ID").with(p -> p.client().id().value().toString()),
                new Column().header("Created At").with(p -> p.createdAt().toString()),
                new Column().header("Last Updated At").with(p -> p.updatedAt() != null ? p.updatedAt().toString() : "Not Updated Yet")
        ));
    }
}
