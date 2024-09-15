package com.wora.component.infrastructure.presistence;

import com.wora.common.infrastructure.mapper.BaseEntityResultSetMapper;
import com.wora.common.infrastructure.persistence.BaseRepository;
import com.wora.common.infrastructure.persistence.BaseRepositoryImpl;
import com.wora.component.domain.entity.Component;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import static com.wora.common.util.QueryExecutor.executeQueryPreparedStatement;
import static com.wora.common.util.QueryExecutor.executeQueryStatement;

public abstract class ComponentRepositoryImpl<Entity extends Component> extends BaseRepositoryImpl<Entity, UUID> implements BaseRepository<Entity, UUID> {
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
    public Optional<Entity> findById(UUID id) {
        final AtomicReference<Optional<Entity>> component = new AtomicReference<>(Optional.empty());
        final String query = String.format("""
                SELECT t.* FROM %s t
                JOIN projects p ON t.project_id = p.id
                WHERE id = ? AND deleted_at IS NULL
                """, tableName);

        executeQueryPreparedStatement(query, stmt -> {
            stmt.setObject(1, id.toString());
            final ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                component.set(Optional.of(mapper.map(rs)));
            }
        });
        return component.get();
    }

}
