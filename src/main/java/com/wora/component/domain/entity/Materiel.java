package com.wora.component.domain.entity;

import com.wora.component.domain.enums.ComponentType;
import com.wora.component.domain.valueObject.ComponentId;
import com.wora.project.domain.entity.Project;

import java.time.LocalDateTime;

public class Materiel extends Component {
    private Double unitCost;
    private Double quantity;
    private Double transportCost;
    private Double qualityCoefficient;

    public Materiel(ComponentId id, String name, Double tva, Project project, Double unitCost, Double quantity, Double transportCost, Double qualityCoefficient) {
        super(id, name, tva, ComponentType.MATERIEL, project);
        this.unitCost = unitCost;
        this.quantity = quantity;
        this.transportCost = transportCost;
        this.qualityCoefficient = qualityCoefficient;
    }


    public Materiel(ComponentId id, String name, Double tva, Project project, Double unitCost, Double quantity, Double transportCost, Double qualityCoefficient, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        super(id, name, tva, ComponentType.MATERIEL, project, createdAt, updatedAt, deletedAt);
        this.unitCost = unitCost;
        this.quantity = quantity;
        this.transportCost = transportCost;
        this.qualityCoefficient = qualityCoefficient;
    }

    public Double unitCost() {
        return unitCost;
    }

    public Materiel setUnitCost(Double unitCost) {
        this.unitCost = unitCost;
        return this;
    }

    public Double quantity() {
        return quantity;
    }

    public Materiel setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public Double transportCost() {
        return transportCost;
    }

    public Materiel setTransportCost(Double transportCost) {
        this.transportCost = transportCost;
        return this;
    }

    public Double qualityCoefficient() {
        return qualityCoefficient;
    }

    public Materiel setQualityCoefficient(Double qualityCoefficient) {
        this.qualityCoefficient = qualityCoefficient;
        return this;
    }

    @Override
    public Double total() {
        return unitCost * quantity * qualityCoefficient + transportCost;
    }

    @Override
    public String toString() {
        return "Materiel{" +
                "unitCost=" + unitCost +
                ", quantity=" + quantity +
                ", transportCost=" + transportCost +
                ", qualityCoefficient=" + qualityCoefficient +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", tva=" + tva +
                ", componentType=" + componentType +
                ", project=" + project +
                '}';
    }
}
