package com.wora.client.application.dto.request;

import com.wora.client.domain.valueObject.Name;

public record ClientRequest(Name name, String phone, String address, Boolean isProfessional) {}
