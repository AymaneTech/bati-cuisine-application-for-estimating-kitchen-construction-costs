package com.wora.client.application.dtos.responses;

import com.wora.client.domain.valueObjects.ClientId;
import com.wora.client.domain.valueObjects.Name;

import java.time.LocalDateTime;

public record ClientResponse(ClientId id, Name name, String phone, String address, Boolean isProfessional,
                             LocalDateTime createdAt, LocalDateTime updatedAt) {
}
