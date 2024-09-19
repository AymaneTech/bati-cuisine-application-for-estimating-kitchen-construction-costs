package com.wora.project.infrastructure.mapper;

import com.wora.client.domain.entity.Client;
import com.wora.client.domain.valueObject.ClientId;
import com.wora.client.domain.valueObject.Name;
import com.wora.client.infrastructure.mapper.ClientResultSetMapper;
import com.wora.common.infrastructure.mapper.BaseEntityResultSetMapper;
import com.wora.project.domain.entity.Project;
import com.wora.project.domain.enums.ProjectStatus;
import com.wora.project.domain.valueObject.ProjectId;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class ProjectResultSetMapper implements BaseEntityResultSetMapper<Project> {

    private final ClientResultSetMapper clientResultSetMapper;

    public ProjectResultSetMapper(ClientResultSetMapper clientResultSetMapper) {
        this.clientResultSetMapper = clientResultSetMapper;
    }

    @Override
    public Project map(final ResultSet rs) throws SQLException {
        return new Project(
                new ProjectId((UUID) rs.getObject("id")),
                rs.getString("name"),
                rs.getDouble("surface"),
                ProjectStatus.valueOf(rs.getString("project_status")),
                new Client(
                        new ClientId((UUID) rs.getObject(11)),
                        new Name(
                                rs.getString("first_name"),
                                rs.getString("last_name")
                        ),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getBoolean("is_professional"),
                        List.of(),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        getDateTime("updated_at", rs),
                        getDateTime("deleted_at", rs)
                ),
                List.of(),
                getDateTime("created_at", rs),
                getDateTime("updated_at", rs),
                getDateTime("deleted_at", rs)
        )
                .setTva(rs.getDouble("tva"))
                .setProfitMargin(rs.getDouble("profit_margin"));
    }

    @Override
    public void map(final Project project, final PreparedStatement stmt) throws SQLException {
        int count = 1;
        stmt.setString(count++, project.name());
        stmt.setDouble(count++, project.surface());
        stmt.setString(count++, project.projectStatus().name());
        stmt.setObject(count++, project.client().id().value());
        stmt.setObject(count++, project.id().value());
    }
}
