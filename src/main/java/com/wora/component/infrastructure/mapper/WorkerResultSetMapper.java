package com.wora.component.infrastructure.mapper;

import com.wora.common.infrastructure.mapper.BaseEntityResultSetMapper;
import com.wora.component.domain.entity.Worker;
import com.wora.component.domain.valueObject.ComponentId;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class WorkerResultSetMapper implements BaseEntityResultSetMapper<Worker> {

    @Override
    public Worker map(final ResultSet rs) throws SQLException {
        return new Worker(
                new ComponentId((UUID) rs.getObject("id")),
                rs.getString("name"),
                rs.getDouble("tva"),
                null,
                rs.getDouble("hourly_rate"),
                rs.getDouble("working_hours"),
                rs.getDouble("productivity"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                getDateTime("updated_at", rs),
                getDateTime("deleted_at", rs)
        );
    }

    @Override
    public void map(final Worker worker, final PreparedStatement stmt) throws SQLException {
        int count = 1;
        stmt.setString(count++, worker.name());
        stmt.setObject(count++, worker.componentType().toString());
        stmt.setDouble(count++, worker.hourlyRate());
        stmt.setDouble(count++, worker.workingHours());
        stmt.setDouble(count++, worker.productivity());
        stmt.setObject(count++, worker.project().id().value());
        stmt.setObject(count++, worker.id().value());
    }
}
