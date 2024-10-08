package com.wora.component.infrastructure.presistence;

import com.wora.component.domain.entity.Materiel;
import com.wora.component.domain.enums.ComponentType;
import com.wora.component.domain.exception.ComponentNotFoundException;
import com.wora.component.domain.repository.ComponentRepository;
import com.wora.component.infrastructure.mapper.MaterielResultSetMapper;

import java.util.UUID;

import static com.wora.common.util.QueryExecutor.executeWithSingleUpdate;

public class MaterielRepositoryImpl extends ComponentRepositoryImpl<Materiel> implements ComponentRepository<Materiel> {
    public MaterielRepositoryImpl(MaterielResultSetMapper mapper) {
        super("materiels", mapper);
    }

    @Override
    public Materiel create(Materiel materiel) {
        final String query = """
                    INSERT INTO materiels (name, tva, component_type, unit_cost, quantity, transport_cost, quality_coefficient, project_id, id)
                    VALUES (?, ?, ?::component_type, ?, ?, ?, ?, ?, ?)
                """;
        executeWithSingleUpdate(query, stmt -> mapper.map(materiel, stmt));
        return findById(materiel.id().value())
                .orElseThrow(() -> new ComponentNotFoundException(materiel.id().value(), ComponentType.MATERIEL));
    }

    @Override
    public Materiel update(UUID id, Materiel materiel) {
        final String query = """
                UPDATE materiels
                SET name = ?, tva = ?, component_type = ?::component_type, unit_cost = ?, quantity = ?,
                transport_cost = ?, quality_coefficient = ?, project_id = ?
                WHERE id = ?
                """;
        executeWithSingleUpdate(query, stmt -> mapper.map(materiel, stmt));
        return findById(id)
                .orElseThrow(() -> new ComponentNotFoundException(id, ComponentType.MATERIEL));
    }
}
