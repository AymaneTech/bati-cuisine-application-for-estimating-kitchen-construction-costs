package com.wora.component.entities;

import com.wora.component.enums.ComponentType;
import com.wora.component.valueObjects.ComponentId;

import java.time.LocalDateTime;

public class Materiel extends Component {

    private Double unitCost;
    private Double quantity;
    private Double transportCost;
    private Double qualityCoefficient;

    public Materiel(ComponentId id, String name, Double tva, Double unitCost, Double quantity, Double transportCost, Double qualityCoefficient) {
        super(id, name, tva, ComponentType.MATERIEL);
        this.unitCost = unitCost;
        this.quantity = quantity;
        this.transportCost = transportCost;
        this.qualityCoefficient = qualityCoefficient;
    }


    public Materiel(ComponentId id, String name, Double tva, Double unitCost, Double quantity, Double transportCost, Double qualityCoefficient, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        super(id, name, tva, ComponentType.MATERIEL, createdAt, updatedAt, deletedAt);
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
}