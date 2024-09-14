package com.wora.client.domain.entities;

import com.wora.client.domain.valueObjects.ClientId;
import com.wora.client.domain.valueObjects.Name;
import com.wora.common.domain.AbstractEntity;

import java.time.LocalDateTime;

public class Client extends AbstractEntity<ClientId> {
    private ClientId id;
    private Name name;
    private String phone;
    private String address;
    private Boolean isProfessional;

    public Client() {
    }

    public Client(ClientId id, Name name, String phone, String address, Boolean isProfessional) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.isProfessional = isProfessional;
    }

    public Client(ClientId id, Name name, String phone, String address, Boolean isProfessional, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        this(id, name, phone, address, isProfessional);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    @Override
    public ClientId getId() {
        return id;
    }

    public Client setId(ClientId id) {
        this.id = id;
        return this;
    }

    public Name getName() {
        return name;
    }

    public Client setName(Name name) {
        this.name = name;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Client setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getIsProfessional() {
        return isProfessional;
    }

    public void setIsProfessional(Boolean professional) {
        isProfessional = professional;
    }
}