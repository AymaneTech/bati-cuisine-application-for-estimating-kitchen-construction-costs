package com.wora.component.infrastructure.mapper;

import com.wora.common.infrastructure.mapper.BaseEntityResultSetMapper;
import com.wora.component.domain.entity.Materiel;
import com.wora.component.domain.valueObject.ComponentId;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class MaterielResultSetMapper implements BaseEntityResultSetMapper<Materiel> {

    @Override
    public Materiel map(final ResultSet rs) throws SQLException {
        return new Materiel(
                new ComponentId((UUID) rs.getObject("id")),
                rs.getString("name"),
                rs.getDouble("tva"),
                null,
                rs.getDouble("unit_cost"),
                rs.getDouble("quantity"),
                rs.getDouble("transport_cost"),
                rs.getDouble("quality_coefficient"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                getDateTime("updated_at", rs),
                getDateTime("deleted_at", rs)
        );
    }

    @Override
    public void map(final Materiel materiel, final PreparedStatement stmt) throws SQLException {
        int count = 1;
        stmt.setString(count++, materiel.name());
        stmt.setDouble(count++, materiel.tva());
        stmt.setObject(count++, materiel.componentType().toString());
        stmt.setDouble(count++, materiel.unitCost());
        stmt.setDouble(count++, materiel.quantity());
        stmt.setDouble(count++, materiel.transportCost());
        stmt.setDouble(count++, materiel.qualityCoefficient());
        stmt.setObject(count++, materiel.project().id().value());
        stmt.setObject(count++, materiel.id().value());
    }
}
