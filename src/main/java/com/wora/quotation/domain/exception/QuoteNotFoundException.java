package com.wora.quotation.domain.exception;

import java.util.UUID;

public class QuoteNotFoundException extends RuntimeException {
    private final UUID quoteId;
    public QuoteNotFoundException(UUID quoteId) {
        super("quote with id " + quoteId + " not found");
        this.quoteId = quoteId;
    }
}
