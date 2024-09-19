package com.wora.quotation.infrastructure.presistence;

import com.wora.common.infrastructure.mapper.BaseEntityResultSetMapper;
import com.wora.common.infrastructure.persistence.BaseRepositoryImpl;
import com.wora.quotation.domain.Quote;
import com.wora.quotation.domain.exception.QuoteNotFoundException;
import com.wora.quotation.domain.repository.QuoteRepository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.wora.common.util.QueryExecutor.executeQueryWithPreparedStatement;
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

    @Override
    public List<Quote> findAllByProjectId(UUID projectId) {
        final List<Quote> quotes = new ArrayList<>();
        final String query = """
                SELECT * FROM quotes
                WHERE project_id = ?
                AND WHERE deleted_at IS NULL
                """;
        executeQueryWithPreparedStatement(query, stmt -> {
            stmt.setObject(1, projectId);
            final ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                quotes.add(mapper.map(rs));
            }
        });
        return quotes;
    }

    @Override
    public List<Quote> findAllByClientId(UUID clientId) {
        final List<Quote> quotes = new ArrayList<>();
        final String query = """
                SELECT q.*
                FROM quotes q
                JOIN projects p ON q.project_id = p.id
                WHERE p.client_id = ?
                AND q.deleted_at IS NULL
                """;
        executeQueryWithPreparedStatement(query, stmt -> {
            stmt.setObject(1, clientId);
            final ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                quotes.add(mapper.map(rs));
            }
        });
        return quotes;
    }

}
