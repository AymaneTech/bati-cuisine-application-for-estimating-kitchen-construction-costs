package com.wora.quotation.application.mapper;

import com.wora.common.application.mapper.BaseEntityDtoMapper;
import com.wora.project.domain.entity.Project;
import com.wora.quotation.application.dto.request.QuoteRequest;
import com.wora.quotation.application.dto.response.QuoteResponse;
import com.wora.quotation.domain.Quote;
import com.wora.quotation.domain.valueObject.QuoteId;

public class QuoteMapper implements BaseEntityDtoMapper<Quote, QuoteRequest, QuoteResponse, QuoteId> {
    @Override
    public Quote map(QuoteRequest dto, QuoteId id) {
        return new Quote(id, dto.issueDate(), dto.validityDate(), dto.accepted(), new Project().setId(dto.projectId()));
    }

    @Override
    public QuoteResponse map(Quote quote) {
        return new QuoteResponse(quote.id(), quote.issueDate(), quote.validityDate(), quote.accepted(), quote.createdAt(), quote.updatedAt());
    }
}
