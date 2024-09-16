package com.wora.component.application.dto.request;

import com.wora.component.domain.enums.ComponentType;
import com.wora.project.domain.valueObject.ProjectId;

public class MaterielRequest extends ComponentRequest{
    private final Double unitCost;
    private final Double quantity;
    private final Double transportCost;
    private final Double qualityCoefficient;

    public MaterielRequest(String name, Double tva, ProjectId projectId, Double unitCost, Double quantity, Double transportCost, Double qualityCoefficient) {
        super(name, tva, ComponentType.MATERIEL, projectId);
        this.unitCost = unitCost;
        this.quantity = quantity;
        this.transportCost = transportCost;
        this.qualityCoefficient = qualityCoefficient;
    }

    public Double unitCost() {
        return unitCost;
    }

    public Double quantity() {
        return quantity;
    }

    public Double transportCost() {
        return transportCost;
    }

    public Double quantityCoefficient() {
        return qualityCoefficient;
    }
}
