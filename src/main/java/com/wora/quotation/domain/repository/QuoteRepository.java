package com.wora.quotation.domain.repository;

import com.wora.common.infrastructure.persistence.BaseRepository;
import com.wora.quotation.domain.Quote;

import java.util.UUID;

public interface QuoteRepository extends BaseRepository<Quote, UUID> {

}
