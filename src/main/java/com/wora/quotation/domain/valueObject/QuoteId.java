package com.wora.quotation.domain.valueObject;

import java.util.UUID;

public record QuoteId(UUID value) {
    public QuoteId() {
        this(UUID.randomUUID());
    }
}
