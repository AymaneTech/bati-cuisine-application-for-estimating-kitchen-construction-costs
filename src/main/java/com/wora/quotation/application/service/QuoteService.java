package com.wora.quotation.application.service;

import com.wora.quotation.application.dto.request.QuoteRequest;
import com.wora.quotation.application.dto.response.QuoteResponse;
import com.wora.quotation.domain.valueObject.QuoteId;

import java.util.List;

public interface QuoteService {
    List<QuoteResponse> findAll();

    QuoteResponse findById(QuoteId id);

    QuoteResponse create(QuoteRequest dto);

    QuoteResponse update(QuoteId id, QuoteRequest dto);

    QuoteResponse delete(QuoteId id);

    boolean existsById(QuoteId id);
}
