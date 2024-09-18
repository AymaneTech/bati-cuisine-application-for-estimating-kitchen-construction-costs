package com.wora.component.application.services.impl;

import com.wora.component.application.dto.request.MaterielRequest;
import com.wora.component.application.dto.response.MaterielResponse;
import com.wora.component.application.mapper.MaterielMapper;
import com.wora.component.application.services.ComponentService;
import com.wora.component.domain.entity.Materiel;
import com.wora.component.domain.enums.ComponentType;
import com.wora.component.domain.exception.ComponentNotFoundException;
import com.wora.component.domain.repository.ComponentRepository;
import com.wora.component.domain.valueObject.ComponentId;
import com.wora.project.domain.valueObject.ProjectId;

import java.util.List;

public class MaterielServiceImpl implements ComponentService<MaterielRequest, MaterielResponse> {
    private final ComponentRepository<Materiel> repository;
    private final MaterielMapper mapper;

    public MaterielServiceImpl(ComponentRepository<Materiel> repository, MaterielMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<MaterielResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::map)
                .toList();
    }

    @Override
    public List<MaterielResponse> findAllByProjectId(ProjectId projectId) {
        return repository.findAllByProjectId(projectId.value())
                .stream().map(mapper::map)
                .toList();
    }

    @Override
    public MaterielResponse findById(ComponentId id) {
        return repository.findById(id.value())
                .map(mapper::map)
                .orElseThrow(() -> new ComponentNotFoundException(id.value(), ComponentType.MATERIEL));
    }

    @Override
    public MaterielResponse create(MaterielRequest dto) {
        System.out.println("here is againt he tva" + dto);
        final Materiel materiel = repository.create(mapper.map(dto, new ComponentId()));
        return mapper.map(materiel);
    }

    @Override
    public MaterielResponse update(ComponentId id, MaterielRequest dto) {
        final Materiel materiel = repository.update(id.value(), mapper.map(dto, id));
        return mapper.map(materiel);
    }

    @Override
    public void delete(ComponentId id) {
        repository.delete(id.value());
    }

    @Override
    public boolean existsById(ComponentId id) {
        return repository.existsById(id.value());
    }


}
