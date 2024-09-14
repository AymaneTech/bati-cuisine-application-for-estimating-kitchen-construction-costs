package com.wora.client.application.dtos.requests;

import com.wora.client.domain.valueObjects.Name;

public record ClientRequest(Name name, String phone, String address, Boolean isProfessional) {}
