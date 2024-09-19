package com.wora.common.infrastructure.persistence;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<Entity, ID> {

    List<Entity> findAll();

    Optional<Entity> findById(ID id);

    Optional<Entity> findByColumn(String columnName, String value);

    Entity create(Entity entity);

    Entity update(ID id, Entity entity);

    void delete(ID id);

    boolean existsById(ID id);

    boolean existsByColumn(String columnName, String value);

    void deleteByColumn(String name, String value);
}
