package com.wora.component.application.dto.response;

import com.wora.component.domain.enums.ComponentType;
import com.wora.component.domain.valueObject.ComponentId;

import java.time.LocalDateTime;

public class MaterielResponse extends ComponentResponse {
    private final Double unitCost;
    private final Double quantity;
    private final Double transportCost;
    private final Double quantityCoefficient;
    private final Double tva;

    public MaterielResponse(ComponentId id, String name, Double tva, String projectId, Double unitCost, Double quantity, Double transportCost, Double quantityCoefficient, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, name, ComponentType.MATERIEL, projectId, createdAt, updatedAt);
        this.unitCost = unitCost;
        this.quantity = quantity;
        this.transportCost = transportCost;
        this.quantityCoefficient = quantityCoefficient;
        this.tva = tva;
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

    public Double tva() {
        return tva;
    }
}
