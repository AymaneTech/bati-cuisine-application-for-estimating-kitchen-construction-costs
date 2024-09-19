package com.wora.project.infrastructure.persistence;

import com.wora.common.infrastructure.mapper.BaseEntityResultSetMapper;
import com.wora.common.infrastructure.persistence.BaseRepositoryImpl;
import com.wora.project.domain.entity.Project;
import com.wora.project.domain.exception.ProjectNotFoundException;
import com.wora.project.domain.repository.ProjectRepository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import static com.wora.common.util.QueryExecutor.*;

public class ProjectRepositoryImpl extends BaseRepositoryImpl<Project, UUID> implements ProjectRepository {
    public ProjectRepositoryImpl(BaseEntityResultSetMapper<Project> mapper) {
        super("projects", mapper);
    }

    @Override
    public List<Project> findAll() {
        final List<Project> projects = new ArrayList<>();
        final String query = """
                SELECT p.*, c.* FROM projects p
                JOIN clients c ON p.client_id = c.id
                WHERE p.deleted_at IS NULL
                """;
        fetchResultWithQuery(query, rs -> {
            while (rs.next()) {
                projects.add(mapper.map(rs));
            }
        });
        return projects;
    }

    @Override
    public Optional<Project> findById(UUID id) {
        final AtomicReference<Optional<Project>> project = new AtomicReference<>(Optional.empty());
        final String query = """
                SELECT p.*, c.* FROM projects p
                JOIN clients c ON p.client_id = c.id
                WHERE p.id = ?
                AND p.deleted_at IS NULL
                """;

        executeQueryWithPreparedStatement(query, stmt -> {
            stmt.setObject(1, id);
            final ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                project.set(Optional.of(mapper.map(rs)));
            }
        });
        return project.get();

    }

    @Override
    public Project create(Project project) {
        final String query = """
                INSERT INTO projects
                (name, surface, project_status, client_id, id)
                VALUES (?, ?, ?::project_status, ?, ?)
                """;
        executeWithSingleUpdate(query, stmt -> mapper.map(project, stmt));
        return findById(project.id().value())
                .orElseThrow(() -> new ProjectNotFoundException(project.id().value()));
    }

    @Override
    public Project update(UUID uuid, Project project) {
        final String query = """
                UPDATE projects
                SET name = ?, surface = ?, project_status = ?::project_status,
                client_id = ?, profit_margin = ?, tva = ?
                WHERE id = ?
                """;
        executeWithSingleUpdate(query, stmt -> {
            int count = 1;
            stmt.setString(count++, project.name());
            stmt.setDouble(count++, project.surface());
            stmt.setObject(count++, project.projectStatus().toString());
            stmt.setObject(count++, project.client().id().value());
            stmt.setDouble(count++, project.profitMargin());
            stmt.setDouble(count++, project.tva());
            stmt.setObject(count++, project.id().value());
        });
        return findById(project.id().value())
                .orElseThrow(() -> new ProjectNotFoundException(project.id().value()));
    }

    @Override
    public Optional<Project> findByName(String name) {
        final AtomicReference<Optional<Project>> project = new AtomicReference<>(Optional.empty());
        final String query = """
                SELECT p.*, c.* FROM projects p
                JOIN clients c ON p.client_id = c.id
                WHERE p.name = ?
                AND p.deleted_at IS NULL
                """;
        executeQueryWithPreparedStatement(query, stmt -> {
            stmt.setObject(1, name);
            final ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                project.set(Optional.of(mapper.map(rs)));
            }
        });
        return project.get();
    }

    @Override
    public List<Project> findByClientId(UUID clientId) {
        final List<Project> projects = new ArrayList<>();
        final String query = """
                SELECT * FROM projects
                WHERE client_id = ?
                AND deleted_at IS NULL
                """;
        executeQueryWithPreparedStatement(query, stmt -> {
            stmt.setObject(1, clientId.toString());
            final ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                projects.add(mapper.map(rs));
            }
        });
        return projects;
    }
}
