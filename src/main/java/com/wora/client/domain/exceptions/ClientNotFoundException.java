package com.wora.client.domain.exceptions;

import java.util.UUID;

public class ClientNotFoundException extends RuntimeException {
    private final UUID id;

    public ClientNotFoundException(UUID id) {
        super("client with id " + id + " not found");
        this.id = id;
    }
}
