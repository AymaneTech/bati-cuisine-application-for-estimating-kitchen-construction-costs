package com.wora.project.application.service;

import com.wora.project.application.dto.request.SaveProjectRequest;

public interface ProjectCostCalculatingService {
    void calculate(SaveProjectRequest dto);
}
