package com.wora.component.infrastructure.presentation;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.wora.common.util.ValidationStrategies;
import com.wora.component.application.dto.request.MaterielRequest;
import com.wora.component.application.dto.response.MaterielResponse;
import com.wora.component.application.services.ComponentService;
import com.wora.project.domain.valueObject.ProjectId;

import java.util.Arrays;
import java.util.List;

import static com.wora.common.util.InputScanner.*;
import static com.wora.common.util.Print.secondaryTitle;

public class MaterielUi {
    private final ComponentService<MaterielRequest, MaterielResponse> service;

    public MaterielUi(ComponentService<MaterielRequest, MaterielResponse> service) {
        this.service = service;
    }

    public void create(ProjectId projectId) {
        secondaryTitle("Please to enter all necessary information!");
        final String name = scanString("Please to enter the materiel name: ", ValidationStrategies.NOT_BLANK);
        final Double tva = scanDouble("Please to enter TVA purcentage:", ValidationStrategies.POSITIVE_DOUBLE);
        final Double unitCost = scanDouble("Please to enter unit cost:", ValidationStrategies.POSITIVE_DOUBLE);
        final Double quantity = scanDouble("Please to enter quantity: ", ValidationStrategies.POSITIVE_DOUBLE);
        final Double transportCost = scanDouble("Please enter the transportation cost: ", ValidationStrategies.POSITIVE_DOUBLE);
        final Double quantityCoefficient = scanDouble("Please to enter quantity coefficient: ", ValidationStrategies.POSITIVE_DOUBLE);

        final MaterielResponse materielResponse = service.create(
                new MaterielRequest(name, tva, projectId, unitCost, quantity, transportCost, quantityCoefficient)
        );
        System.out.println(getTable(List.of(materielResponse)));
        System.out.println("the materiel created successfully!");

        executeIfUserConfirms("Add new One or not ",
                () -> this.create(projectId)
        );
    }

    public void showByProjectId(ProjectId id) {
        final List<MaterielResponse> materiels = service.findAllByProjectId(id);
        System.out.println(getTable(materiels));
    }

    public void showTotalCostOfMateriels(ProjectId id) {
        final Double total = service.calculateTotalCost(service.findAllByProjectId(id));
        final Double totalWithTva = service.calculateTotalCostWithTva(service.findAllByProjectId(id));
        System.out.println("--- Total cost without tva: " + total);
        System.out.println("--- Total cost with tva: " + totalWithTva);
    }

    private String getTable(List<MaterielResponse> materiels) {
        return AsciiTable.getTable(materiels, Arrays.asList(
                new Column().with(materiel -> materiel.id().value().toString()),
                new Column().header("Name").with(MaterielResponse::name),
                new Column().header("TVA").with(m -> m.tva().toString()),
                new Column().header("Unit Cost").with(m -> m.unitCost().toString()),
                new Column().header("Quantity").with(m -> m.quantity().toString()),
                new Column().header("Transport Cost").with(m -> m.transportCost().toString()),
                new Column().header("Quality Coefficient").with(m -> m.qualityCoefficient().toString()),
                new Column().header("Total").with(m -> m.total().toString()),
                new Column().header("Total with Tva").with(m -> m.totalWithTva().toString()),
                new Column().header("Created At").with(materiel -> materiel.createdAt().toString()),
                new Column().header("Last Updated At").with(materiel -> materiel.updatedAt() != null ? materiel.updatedAt().toString() : "Not Updated Yet")
        ));
    }
}
