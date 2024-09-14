package com.wora.client.domain.repositories;

import com.wora.client.domain.entities.Client;
import com.wora.common.infrastructure.persistence.BaseRepository;

import java.util.UUID;

public interface ClientRepository extends BaseRepository<Client, UUID> {
}
