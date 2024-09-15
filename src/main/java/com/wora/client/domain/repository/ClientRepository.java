package com.wora.client.domain.repository;

import com.wora.client.domain.entity.Client;
import com.wora.common.infrastructure.persistence.BaseRepository;

import java.util.UUID;

public interface ClientRepository extends BaseRepository<Client, UUID> {

    Boolean existsByAddress(String address);

    Boolean existsByPhone(String phone);
}
