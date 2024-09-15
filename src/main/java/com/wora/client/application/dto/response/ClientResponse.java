package com.wora.client.application.dto.response;

import com.wora.client.domain.valueObject.ClientId;
import com.wora.client.domain.valueObject.Name;

import java.time.LocalDateTime;

public record ClientResponse(ClientId id, Name name, String phone, String address, Boolean isProfessional,
                             LocalDateTime createdAt, LocalDateTime updatedAt) {
}
