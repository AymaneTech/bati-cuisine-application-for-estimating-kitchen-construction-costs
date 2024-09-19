package com.wora.quotation.domain.repository;

import com.wora.common.infrastructure.persistence.BaseRepository;
import com.wora.quotation.domain.Quote;

import java.util.List;
import java.util.UUID;

public interface QuoteRepository extends BaseRepository<Quote, UUID> {

    List<Quote> findAllByProjectId(UUID projectId);

    List<Quote> findAllByClientId(UUID clientId);
}
