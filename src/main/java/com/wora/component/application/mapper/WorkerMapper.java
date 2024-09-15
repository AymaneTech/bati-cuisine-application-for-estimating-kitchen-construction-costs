package com.wora.component.application.mapper;

import com.wora.common.application.mapper.BaseEntityDtoMapper;
import com.wora.component.application.dto.request.WorkerRequest;
import com.wora.component.application.dto.response.WorkerResponse;
import com.wora.component.domain.entity.Worker;
import com.wora.component.domain.valueObject.ComponentId;

public class WorkerMapper implements BaseEntityDtoMapper<Worker, WorkerRequest, WorkerResponse, ComponentId> {

    @Override
    public Worker map(WorkerRequest dto, ComponentId id) {
        return new Worker(
                id,
                dto.name(),
                Double.parseDouble(dto.tva()),
                dto.hourlyRate(),
                dto.workingHours(),
                dto.productivity()
        );
    }

    @Override
    public WorkerResponse map(Worker worker) {
        return new WorkerResponse(
                worker.id(),
                worker.name(),
                String.valueOf(worker.tva()),
                worker.project() != null ? worker.project().id().toString() : null,
                worker.hourlyRate(),
                worker.workingHours(),
                worker.productivity(),
                worker.createdAt(),
                worker.updatedAt()
        );
    }
}
