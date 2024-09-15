package com.wora.component.domain.exception;

import com.wora.component.domain.enums.ComponentType;

import java.util.UUID;

public class ComponentNotFoundException extends RuntimeException {
    private final UUID id;
    private final ComponentType type;

    public ComponentNotFoundException(UUID id, ComponentType type) {
        super("cannot find component with id " + id + " and type " + type);
        this.id = id;
        this.type = type;
    }
}
