package com.wora.quotation.infrastructure.mapper;

import com.wora.common.infrastructure.mapper.BaseEntityResultSetMapper;
import com.wora.project.domain.entity.Project;
import com.wora.quotation.domain.Quote;
import com.wora.quotation.domain.valueObject.QuoteId;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class QuoteResultSetMapper implements BaseEntityResultSetMapper<Quote> {
    @Override
    public Quote map(ResultSet rs) throws SQLException {
        return new Quote(
                new QuoteId(UUID.fromString(rs.getString("id"))),
                getDate("issue_date", rs),
                getDate("validity_date", rs),
                rs.getBoolean("accepted"),
                new Project(),
                getDateTime("created_at", rs),
                getDateTime("updated_at", rs)
        );
    }

    @Override
    public void map(Quote quote, PreparedStatement stmt) throws SQLException {
        int count = 1;
        stmt.setDate(count++, Date.valueOf(quote.issueDate()));
        stmt.setDate(count++, Date.valueOf(quote.validityDate()));
        stmt.setBoolean(count++, quote.accepted());
        stmt.setObject(count++, quote.project().id().value());
        stmt.setObject(count++, quote.id().value());
    }
}
