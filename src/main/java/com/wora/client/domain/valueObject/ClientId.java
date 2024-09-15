package com.wora.client.domain.valueObject;

import java.util.UUID;

public record ClientId(UUID value) {
    public ClientId() {
        this(UUID.randomUUID());
    }
}
