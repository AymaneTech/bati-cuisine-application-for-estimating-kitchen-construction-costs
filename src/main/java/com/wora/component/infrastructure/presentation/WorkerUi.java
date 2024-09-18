package com.wora.component.infrastructure.presentation;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.wora.common.util.ValidationStrategies;
import com.wora.component.application.dto.request.WorkerRequest;
import com.wora.component.application.dto.response.WorkerResponse;
import com.wora.component.application.services.ComponentService;
import com.wora.project.domain.valueObject.ProjectId;

import java.util.Arrays;
import java.util.List;

import static com.wora.common.util.InputScanner.*;
import static com.wora.common.util.Print.secondaryTitle;

public class WorkerUi {
    private final ComponentService<WorkerRequest, WorkerResponse> service;

    public WorkerUi(ComponentService<WorkerRequest, WorkerResponse> service) {
        this.service = service;
    }

    public void create(ProjectId id) {
        secondaryTitle("Please enter all necessary information!");
        final String name = scanString("Please enter the worker name: ", ValidationStrategies.NOT_BLANK);
        final Double tva = scanDouble("Please enter TVA percentage:", ValidationStrategies.POSITIVE_DOUBLE);
        final Double hourlyRate = scanDouble("Please enter hourly rate:", ValidationStrategies.POSITIVE_DOUBLE);
        final Double workingHours = scanDouble("Please enter working hours: ", ValidationStrategies.POSITIVE_DOUBLE);
        final Double productivity = scanDouble("Please enter productivity: ", ValidationStrategies.POSITIVE_DOUBLE);

        final WorkerResponse workerResponse = service.create(
                new WorkerRequest(name, tva, id, hourlyRate, workingHours, productivity)
        );
        System.out.println(getTable(List.of(workerResponse)));
        System.out.println("The worker was created successfully!");

        executeIfUserConfirms("Add new one or not ",
                () -> this.create(id)
        );
    }

    public void showByProjectId(ProjectId id) {
        final List<WorkerResponse> workers = service.findAllByProjectId(id);
        System.out.println(getTable(workers));
    }

    public void showTotalCostOfWorkers(ProjectId id) {
        final Double total = service.calculateTotalCost(service.findAllByProjectId(id));
        final Double totalWithTva = service.calculateTotalCostWithTva(service.findAllByProjectId(id));
        System.out.println("--- Total cost without tva: " + total);
        System.out.println("--- Total cost with tva: " + totalWithTva);

    }

    private String getTable(List<WorkerResponse> workers) {
        return AsciiTable.getTable(workers, Arrays.asList(
                new Column().with(worker -> worker.id().value().toString()),
                new Column().header("Name").with(WorkerResponse::name),
                new Column().header("TVA").with(w -> w.tva().toString()),
                new Column().header("Hourly Rate").with(w -> w.hourlyRate().toString()),
                new Column().header("Working Hours").with(w -> w.workingHours().toString()),
                new Column().header("Productivity").with(w -> w.productivity().toString()),
                new Column().header("Total").with(w -> w.total().toString()),
                new Column().header("Total with tva").with(w -> w.totalWithTva().toString()),
                new Column().header("Created At").with(worker -> worker.createdAt().toString()),
                new Column().header("Last Updated At").with(worker -> worker.updatedAt() != null ? worker.updatedAt().toString() : "Not Updated Yet")
        ));
    }

}