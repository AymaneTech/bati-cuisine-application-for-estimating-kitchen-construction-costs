package com.wora.client.domain.entities;

import com.wora.client.domain.valueObjects.ClientId;
import com.wora.client.domain.valueObjects.Name;
import com.wora.common.domain.AbstractEntity;

import java.time.LocalDateTime;
import java.util.List;

public class Client extends AbstractEntity<ClientId> {
    private ClientId id;
    private Name name;
    private String phone;
    private String address;
    private Boolean isProfessional;
    // TODO: add project list
    private List<?> projects;

    public Client() {
    }

    public Client(ClientId id, Name name, String phone, String address, Boolean isProfessional, List<?> projects) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.isProfessional = isProfessional;
        this.projects = projects;
    }

    public Client(ClientId id, Name name, String phone, String address, Boolean isProfessional, List<?> projects, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        this(id, name, phone, address, isProfessional, projects);
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

    public Client setAddress(String address) {
        this.address = address;
        return this;
    }

    public Boolean getIsProfessional() {
        return isProfessional;
    }

    public Client setIsProfessional(Boolean professional) {
        isProfessional = professional;
        return this;
    }

    public List<?> getProjects() {
        return projects;
    }

    public Client setProjects(List<?> projects) {
        this.projects = projects;
        return this;
    }
}