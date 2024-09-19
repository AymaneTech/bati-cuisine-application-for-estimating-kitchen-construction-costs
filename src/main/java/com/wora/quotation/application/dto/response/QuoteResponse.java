package com.wora.quotation.application.dto.response;

import com.wora.quotation.domain.valueObject.QuoteId;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record QuoteResponse(QuoteId id, LocalDate issueDate, LocalDate validityDate, Boolean accepted,
                            LocalDateTime createdAt, LocalDateTime updatedAt) {
}
