package com.wora.component.infrastructure.presistence;

import com.wora.component.domain.entity.Worker;
import com.wora.component.domain.enums.ComponentType;
import com.wora.component.domain.exception.ComponentNotFoundException;
import com.wora.component.domain.repository.ComponentRepository;
import com.wora.component.infrastructure.mapper.WorkerResultSetMapper;

import java.util.UUID;

import static com.wora.common.util.QueryExecutor.executeWithSingleUpdate;

public class WorkerRepositoryImpl extends ComponentRepositoryImpl<Worker> implements ComponentRepository<Worker> {
    public WorkerRepositoryImpl(WorkerResultSetMapper mapper) {
        super("workers", mapper);
    }


    @Override
    public Worker create(Worker materiel) {
        final String query = """
                INSERT INTO workers (name, component_type, hourly_rate, working_hours, productivity, project_id, id)
                VALUES (?, ?::component_type, ?, ?, ?, ?, ?)
                """;
        executeWithSingleUpdate(query, stmt -> mapper.map(materiel, stmt));
        return findById(materiel.id().value())
                .orElseThrow(() -> new ComponentNotFoundException(materiel.id().value(), ComponentType.WORKER));
    }

    @Override
    public Worker update(UUID uuid, Worker materiel) {
        final String query = """
                UPDATE workers
                SET name = ?, component_type = ?::component_type, hourly_rate = ?,
                working_hours = ?, productivity = ?, project_id = ?
                WHERE id = ?
                """;
        executeWithSingleUpdate(query, stmt -> mapper.map(materiel, stmt));
        return findById(materiel.id().value())
                .orElseThrow(() -> new ComponentNotFoundException(materiel.id().value(), ComponentType.WORKER));
    }
}
