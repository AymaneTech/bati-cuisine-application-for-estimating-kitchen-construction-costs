package com.wora.component.application.services;

import com.wora.client.application.dto.request.ClientRequest;
import com.wora.component.application.dto.request.ComponentRequest;
import com.wora.component.application.dto.response.ComponentResponse;
import com.wora.component.domain.valueObject.ComponentId;

import java.util.List;

public interface ComponentService<RequestDto extends ComponentRequest, ResponseDto extends ComponentResponse> {
    List<ResponseDto> findAll();

    ResponseDto findById(ComponentId id);

    ResponseDto create(RequestDto dto);

    ResponseDto update(ComponentId id, RequestDto dto);

    void delete(ComponentId id);

    Boolean existsById(ComponentId id);
}
