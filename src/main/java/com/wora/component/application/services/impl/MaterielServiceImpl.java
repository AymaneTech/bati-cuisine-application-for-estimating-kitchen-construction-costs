package com.wora.component.application.services.impl;

import com.wora.component.application.dto.request.MaterielRequest;
import com.wora.component.application.dto.response.MaterielResponse;
import com.wora.component.application.mapper.MaterielMapper;
import com.wora.component.application.services.ComponentService;
import com.wora.component.domain.entity.Materiel;
import com.wora.component.domain.repository.ComponentRepository;
import com.wora.component.domain.valueObject.ComponentId;

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
        return List.of();
    }

    @Override
    public MaterielResponse findById(ComponentId id) {
        return null;
    }

    @Override
    public MaterielResponse create(MaterielRequest materielRequest) {
        return null;
    }

    @Override
    public MaterielResponse update(ComponentId id, MaterielRequest materielRequest) {
        return null;
    }

    @Override
    public void delete(ComponentId id) {

    }

    @Override
    public Boolean existsById(ComponentId id) {
        return null;
    }
}
