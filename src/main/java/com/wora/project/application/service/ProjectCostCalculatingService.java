package com.wora.project.application.service;

import com.wora.project.application.dto.request.SaveProjectRequest;

public interface ProjectCostCalculatingService {
    Double calculateWithoutProfitMargin(SaveProjectRequest dto);

    Double calculateProfitMargin(SaveProjectRequest dto);

    Double calculateWithProfitMargin(SaveProjectRequest dto);
}
