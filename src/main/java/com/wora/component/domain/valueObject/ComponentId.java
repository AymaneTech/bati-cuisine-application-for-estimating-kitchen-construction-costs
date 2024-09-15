package com.wora.component.domain.valueObject;

import java.util.UUID;

public record ComponentId(UUID value) {
    public ComponentId() {
        this(UUID.randomUUID());
    }
}
