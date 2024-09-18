package com.wora.project.application.service;

import com.wora.project.application.dto.request.SaveProjectRequest;

public interface ProjectReportService {
    Double calculateWithoutProfitMargin(SaveProjectRequest dto);

    Double calculateProfitMargin(SaveProjectRequest dto);

    Double calculateWithProfitMargin(SaveProjectRequest dto);
}
