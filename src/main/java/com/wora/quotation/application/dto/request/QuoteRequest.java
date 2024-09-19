package com.wora.quotation.application.dto.request;

import com.wora.project.domain.valueObject.ProjectId;

import java.time.LocalDate;

public record QuoteRequest(LocalDate issueDate, LocalDate validityDate, Boolean accepted, ProjectId projectId) {

}
