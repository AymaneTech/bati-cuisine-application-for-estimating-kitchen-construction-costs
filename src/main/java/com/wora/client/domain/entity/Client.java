package com.wora.client.domain.entity;

import com.wora.client.domain.valueObject.ClientId;
import com.wora.client.domain.valueObject.Name;
import com.wora.common.domain.AbstractEntity;
import com.wora.project.entity.Project;

import java.time.LocalDateTime;
import java.util.List;

public class Client extends AbstractEntity<ClientId> {
    private ClientId id;
    private Name name;
    private String phone;
    private String address;
    private Boolean isProfessional;
    private List<Project> projects;

    public Client() {
    }

    public Client(ClientId id, Name name, String phone, String address, Boolean isProfessional, List<Project> projects) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.isProfessional = isProfessional;
        this.projects = projects;
    }

    public Client(ClientId id, Name name, String phone, String address, Boolean isProfessional, List<Project> projects, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        this(id, name, phone, address, isProfessional, projects);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    @Override
    public ClientId id() {
        return id;
    }

    public Client setId(ClientId id) {
        this.id = id;
        return this;
    }

    public Name name() {
        return name;
    }

    public Client setName(Name name) {
        this.name = name;
        return this;
    }

    public String phone() {
        return phone;
    }

    public Client setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String address() {
        return address;
    }

    public Client setAddress(String address) {
        this.address = address;
        return this;
    }

    public Boolean isProfessional() {
        return isProfessional;
    }

    public Client setProfessional(Boolean professional) {
        isProfessional = professional;
        return this;
    }

    public List<?> getProjects() {
        return projects;
    }

    public Client setProjects(List<Project> projects) {
        this.projects = projects;
        return this;
    }
}