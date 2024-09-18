package com.wora.component.application.services;

import com.wora.component.application.dto.request.ComponentRequest;
import com.wora.component.application.dto.response.ComponentResponse;
import com.wora.component.domain.valueObject.ComponentId;
import com.wora.project.domain.valueObject.ProjectId;

import java.util.List;

public interface ComponentService<RequestDto extends ComponentRequest, ResponseDto extends ComponentResponse> {
    List<ResponseDto> findAll();

    List<ResponseDto> findAllByProjectId(ProjectId projectId);

    ResponseDto findById(ComponentId id);

    ResponseDto create(RequestDto dto);

    ResponseDto update(ComponentId id, RequestDto dto);

    void delete(ComponentId id);

    boolean existsById(ComponentId id);

    default Double calculateTotalCost(List<ResponseDto> components) {
        return components.stream().map(ResponseDto::total)
                .reduce(0.0, Double::sum);
    }

    default Double calculateTotalCostWithTva(List<ResponseDto> components) {
       return components.stream().map(ResponseDto::totalWithTva)
               .reduce(0.0, Double::sum);
    }
}