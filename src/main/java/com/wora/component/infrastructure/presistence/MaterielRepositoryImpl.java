package com.wora.component.infrastructure.presistence;

import com.wora.common.infrastructure.persistence.BaseRepository;
import com.wora.common.infrastructure.persistence.BaseRepositoryImpl;
import com.wora.component.domain.entity.Materiel;
import com.wora.component.domain.enums.ComponentType;
import com.wora.component.domain.exception.ComponentNotFoundException;
import com.wora.component.infrastructure.mapper.MaterielResultSetMapper;

import java.util.UUID;

import static com.wora.common.util.QueryExecutor.execute;


public class MaterielRepositoryImpl extends BaseRepositoryImpl<Materiel, UUID> implements BaseRepository<Materiel, UUID> {
    public MaterielRepositoryImpl(MaterielResultSetMapper mapper) {
        super("materiels", mapper);
    }

    @Override
    public Materiel create(Materiel materiel) {
        final String query = """
                INSERT INTO materiels (name, tva, component_type, unit_cost, quantity, transport_cost, quantity_coefficient, project_id, id)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;
        execute(query, stmt -> mapper.map(materiel, stmt));
        return findById(materiel.id().value())
                .orElseThrow(() -> new ComponentNotFoundException(materiel.id().value(), ComponentType.MATERIEL));
    }

    @Override
    public Materiel update(UUID uuid, Materiel materiel) {
        final String query = """
                UPDATE materiels
                SET name = ?, tva = ?, component_type = ?::component_type, unit_cost = ?, quantity = ?,
                transport_cost = ?, quantity_coefficient = ?, project_id = ?
                WHERE id = ?
                """;
        execute(query, stmt -> mapper.map(materiel, stmt));
        return findById(materiel.id().value())
                .orElseThrow(() -> new ComponentNotFoundException(materiel.id().value(), ComponentType.MATERIEL));
    }
}
