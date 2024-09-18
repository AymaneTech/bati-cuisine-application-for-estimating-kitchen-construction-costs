package com.wora.component.infrastructure.presistence;

import com.wora.common.infrastructure.mapper.BaseEntityResultSetMapper;
import com.wora.common.infrastructure.persistence.BaseRepositoryImpl;
import com.wora.component.domain.entity.Component;
import com.wora.component.domain.repository.ComponentRepository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.wora.common.util.QueryExecutor.executeQueryPreparedStatement;
import static com.wora.common.util.QueryExecutor.executeQueryStatement;

public abstract class ComponentRepositoryImpl<Entity extends Component> extends BaseRepositoryImpl<Entity, UUID> implements ComponentRepository<Entity> {
    public ComponentRepositoryImpl(String tableName, BaseEntityResultSetMapper<Entity> mapper) {
        super(tableName, mapper);
    }

    @Override
    public List<Entity> findAll() {
        final List<Entity> components = new ArrayList<>();
        final String query = String.format("""
                SELECT t.* FROM %s t
                JOIN projects p ON t.project_id = p.id
                WHERE deleted_at IS NULL
                """, tableName);
        executeQueryStatement(query, rs -> {
            while (rs.next()) {
                components.add(mapper.map(rs));
            }
        });
        return components;
    }


    @Override
    public List<Entity> findAllByProjectId(UUID id) {
        final List<Entity> materiels = new ArrayList<>();
        final String query = "SELECT * FROM " + tableName + " WHERE project_id = ? AND deleted_at IS NULL";
        executeQueryPreparedStatement(query, stmt -> {
            stmt.setObject(1, id);
            final ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                materiels.add(mapper.map(rs));
            }
        });
        return materiels;
    }
}
