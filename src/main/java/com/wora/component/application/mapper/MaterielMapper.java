package com.wora.component.application.mapper;

import com.wora.common.application.mapper.BaseEntityDtoMapper;
import com.wora.component.application.dto.request.MaterielRequest;
import com.wora.component.application.dto.response.MaterielResponse;
import com.wora.component.domain.entity.Materiel;
import com.wora.component.domain.valueObject.ComponentId;
import com.wora.project.domain.entity.Project;

public class MaterielMapper implements BaseEntityDtoMapper<Materiel, MaterielRequest, MaterielResponse, ComponentId> {

    @Override
    public Materiel map(MaterielRequest dto, ComponentId id) {
        return new Materiel(
                id,
                dto.name(),
                dto.tva(),
                new Project().setId(dto.projectId()),
                dto.unitCost(),
                dto.quantity(),
                dto.transportCost(),
                dto.quantityCoefficient());
    }

    @Override
    public MaterielResponse map(Materiel materiel) {
        return new MaterielResponse(
                materiel.id(),
                materiel.name(),
                String.valueOf(materiel.tva()),
                materiel.project() != null ? materiel.project().id().toString() : null,
                materiel.unitCost(),
                materiel.quantity(),
                materiel.transportCost(),
                materiel.qualityCoefficient(),
                materiel.createdAt(),
                materiel.updatedAt());
    }
}
