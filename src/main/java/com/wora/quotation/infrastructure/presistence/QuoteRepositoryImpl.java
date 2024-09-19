package com.wora.quotation.infrastructure.presistence;

import com.wora.common.infrastructure.mapper.BaseEntityResultSetMapper;
import com.wora.common.infrastructure.persistence.BaseRepositoryImpl;
import com.wora.quotation.domain.Quote;
import com.wora.quotation.domain.exception.QuoteNotFoundException;
import com.wora.quotation.domain.repository.QuoteRepository;

import java.util.UUID;

import static com.wora.common.util.QueryExecutor.executeWithSingleUpdate;

public class QuoteRepositoryImpl extends BaseRepositoryImpl<Quote, UUID> implements QuoteRepository {

    public QuoteRepositoryImpl(BaseEntityResultSetMapper<Quote> mapper) {
        super("quotes", mapper);
    }

    @Override
    public Quote create(Quote quote) {
        final String query = """
                INSERT INTO quotes (issue_date, validity_date, accepted, project_id, id)
                VALUES (?,?,?,?,?)
                """;
        executeWithSingleUpdate(query, stmt -> mapper.map(quote, stmt));
        return findById(quote.id().value())
                .orElseThrow(() -> new QuoteNotFoundException(quote.id().value()));

    }

    @Override
    public Quote update(UUID id, Quote quote) {
        final String query = """
                UPDATE quotes
                SET issue_date = ?, validity_date = ?, accepted = ?, project_id = ?
                WHERE id = ?
                """;
        executeWithSingleUpdate(query, stmt -> mapper.map(quote, stmt));
        return findById(id)
                .orElseThrow(() -> new QuoteNotFoundException(id));
    }
}
