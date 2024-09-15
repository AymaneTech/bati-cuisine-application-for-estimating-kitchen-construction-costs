package com.wora.component.application.dto.request;

import com.wora.component.domain.enums.ComponentType;

public class MaterielRequest extends ComponentRequest{
    private final Double unitCost;
    private final Double quantity;
    private final Double transportCost;
    private final Double quantityCoefficient;

    public MaterielRequest(String name, String tva, String projectId, Double unitCost, Double quantity, Double transportCost, Double quantityCoefficient) {
        super(name, tva, ComponentType.MATERIEL, projectId);
        this.unitCost = unitCost;
        this.quantity = quantity;
        this.transportCost = transportCost;
        this.quantityCoefficient = quantityCoefficient;
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
        return quantityCoefficient;
    }
}
