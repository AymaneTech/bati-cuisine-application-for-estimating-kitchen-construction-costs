package com.wora.project.domain.valueObject;

import java.util.UUID;

public record ProjectId(UUID value) {
    public ProjectId() {
        this(UUID.randomUUID());
    }
}
