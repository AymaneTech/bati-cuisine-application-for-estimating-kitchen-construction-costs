package com.wora.common.application.mapper;

import com.wora.common.domain.AbstractEntity;

public interface BaseEntityDtoMapper<Entity, Req, Res, ID> {

    Entity map(Req req, ID id);

    Res map(Entity entity);
}
