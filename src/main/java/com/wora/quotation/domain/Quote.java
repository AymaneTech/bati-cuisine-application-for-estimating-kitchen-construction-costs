package com.wora.quotation.domain;

import com.wora.project.domain.entity.Project;
import com.wora.quotation.domain.valueObject.QuoteId;

import java.time.LocalDate;

public class Quote {
    private QuoteId id;
    private LocalDate issueDate;
    private LocalDate validityDate;
    private Boolean accepted;
    private Project project;

    public Quote(QuoteId id, LocalDate issueDate, LocalDate validityDate, Boolean accepted, Project project) {
        this.id = id;
        this.issueDate = issueDate;
        this.validityDate = validityDate;
        this.accepted = accepted;
        this.project = project;
    }

    public QuoteId id() {
        return id;
    }

    public Quote setId(QuoteId id) {
        this.id = id;
        return this;
    }

    public LocalDate issueDate() {
        return issueDate;
    }

    public Quote setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
        return this;
    }

    public LocalDate validityDate() {
        return validityDate;
    }

    public Quote setValidityDate(LocalDate validityDate) {
        this.validityDate = validityDate;
        return this;
    }

    public Boolean accepted() {
        return accepted;
    }

    public Quote setAccepted(Boolean accepted) {
        this.accepted = accepted;
        return this;
    }

    public Project project() {
        return project;
    }

    public Quote setProject(Project project) {
        this.project = project;
        return this;
    }
}
