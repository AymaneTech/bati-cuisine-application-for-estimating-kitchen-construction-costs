package com.wora.quotation.infrastructure.presentation;

import com.wora.common.util.ValidationStrategies;
import com.wora.project.domain.valueObject.ProjectId;
import com.wora.quotation.application.dto.request.QuoteRequest;
import com.wora.quotation.application.service.QuoteService;

import java.time.LocalDate;

import static com.wora.common.util.InputScanner.*;

public class QuoteUi {
    private final QuoteService service;

    public QuoteUi(QuoteService service) {
        this.service = service;
    }

    public void create(ProjectId projectId) {
        final LocalDate issueDate = scanDate("Please enter the issue date (yyyy-MM-dd): ", ValidationStrategies.FUTURE_DATE);
        final LocalDate validityDate = scanDate("Please enter the issue date (yyyy-mm-dd): ", ValidationStrategies.combine(
                ValidationStrategies.FUTURE_DATE,
                issueDate::isBefore
        ));
        final Boolean accepted = scanBoolean("is this quotation accepted by client (y/n): ");

        executeIfUserConfirms("Do you want to save Quote ", () -> {
            service.create(new QuoteRequest(issueDate, validityDate, accepted, projectId));
            System.out.println("Quote saved.");
        });
    }
}
