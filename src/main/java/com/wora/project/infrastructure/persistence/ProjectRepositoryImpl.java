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

import static com.wora.common.util.QueryExecutor.execute;
import static com.wora.common.util.QueryExecutor.executeQueryPreparedStatement;

public class ProjectRepositoryImpl extends BaseRepositoryImpl<Project, UUID> implements ProjectRepository {
    public ProjectRepositoryImpl(BaseEntityResultSetMapper<Project> mapper) {
        super("projects", mapper);
    }

    @Override
    public Project create(Project project) {
        final String query = """
                INSERT INTO projects
                (name, surface, total_cost, project_status, client_id, id)
                VALUES (?, ?, ?? ?, ?, ?)
                """;
        execute(query, stmt -> mapper.map(project, stmt));
        return findById(project.id().value())
                .orElseThrow(() -> new ProjectNotFoundException(project.id().value()));
    }

    @Override
    public Project update(UUID uuid, Project project) {
        final String query = """
                UPDATE projects
                SET name = ?, surface = ?, total_cost = ?, client_id = ?
                WHERE id = ?
                """;
        execute(query, stmt -> mapper.map(project, stmt));
        return findById(project.id().value())
                .orElseThrow(() -> new ProjectNotFoundException(project.id().value()));
    }

    @Override
    public Optional<Project> findByName(String name) {
        return findByColumn("name", name);
    }

    @Override
    public List<Project> findByClientId(UUID clientId) {
        final List<Project> projects = new ArrayList<>();
        final String query = """
                SELECT * FROM projects
                WHERE client_id = ?
                AND deleted_at IS NULL
                """;
        executeQueryPreparedStatement(query, stmt -> {
            stmt.setObject(1, clientId.toString());
            final ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                projects.add(mapper.map(rs));
            }
        });
        return projects;
    }
}
