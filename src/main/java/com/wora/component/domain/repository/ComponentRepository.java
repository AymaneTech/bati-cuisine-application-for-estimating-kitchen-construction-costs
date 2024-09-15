package com.wora.component.domain.repository;

import com.wora.common.infrastructure.persistence.BaseRepository;
import com.wora.component.domain.entity.Component;

import java.util.UUID;

public interface ComponentRepository extends BaseRepository<Component, UUID> {
}
