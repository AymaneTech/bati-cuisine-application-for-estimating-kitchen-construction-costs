package com.wora.project.domain.exception;

import java.util.UUID;

public class ProjectNotFoundException extends RuntimeException {
    private final UUID id;
    private final String name;

    public ProjectNotFoundException(UUID id) {
        super("Project with id " + id + " not found!");
        this.id = id;
        this.name = null;
    }

    public ProjectNotFoundException(String name) {
        super("project with name " + name + " not found");
        this.id = null;
        this.name = name;
    }
}
