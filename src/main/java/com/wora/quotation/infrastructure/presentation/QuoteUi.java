package com.wora.quotation.infrastructure.presentation;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.wora.client.domain.valueObject.ClientId;
import com.wora.common.util.ValidationStrategies;
import com.wora.mainMenu.MainMenuUi;
import com.wora.project.domain.valueObject.ProjectId;
import com.wora.quotation.application.dto.request.QuoteRequest;
import com.wora.quotation.application.dto.response.QuoteResponse;
import com.wora.quotation.application.service.QuoteService;
import com.wora.quotation.domain.valueObject.QuoteId;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.wora.common.util.InputScanner.*;
import static com.wora.common.util.Print.title;

public class QuoteUi {
    private final QuoteService service;
    private MainMenuUi menuUi;

    public QuoteUi(QuoteService service) {
        this.service = service;
    }

    public void showMenu() {
        boolean exit = false;

        while (!exit) {
            title("===== Quote Menu =====");
            System.out.println("1. Delete Quote");
            System.out.println("2. Find Quote by ID");
            System.out.println("3. Find Quotes by Client");
            System.out.println("4. Find Quotes by Project");
            System.out.println("5. Back to Main Menu");
            System.out.println("0. Exit");

            Integer userChoice = scanInt("Please choose an option: ", ValidationStrategies.POSITIVE_INT);

            switch (userChoice) {
                case 1 -> delete();
                case 2 -> findById();
                case 3 -> findByClient();
                case 4 -> findByProject();
                case 5 -> menuUi.showMenu();
                case 0 -> exit = true;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void create(ProjectId projectId) {
        final LocalDate issueDate = scanDate("Please enter the issue date (yyyy-MM-dd): ", ValidationStrategies.FUTURE_DATE);
        final LocalDate validityDate = scanDate("Please enter the validity date (yyyy-MM-dd): ", ValidationStrategies.combine(
                ValidationStrategies.FUTURE_DATE,
                issueDate::isBefore
        ));
        final Boolean accepted = scanBoolean("Is this quotation accepted by the client (y/n): ");

        executeIfUserConfirms("Do you want to save this quote? ", () -> {
            service.create(new QuoteRequest(issueDate, validityDate, accepted, projectId));
            System.out.println("Quote saved.");
        });
    }

    public void delete() {
        final QuoteId quoteId = scanQuoteId("Please enter the quote ID to delete: ");
        executeIfUserConfirms("Are you sure you want to delete this quote? ", () -> {
            service.delete(quoteId);
            System.out.println("Quote deleted successfully.");
        });
    }

    public void findById() {
        final QuoteId quoteId = scanQuoteId("Please enter the quote ID: ");
        QuoteResponse quote = service.findById(quoteId);
        System.out.println(getQuoteTable(List.of(quote)));
    }

    public void findByClient() {
        final UUID clientId = scanUuid("Please enter the client ID: ", ValidationStrategies.VALID_UUID);
        List<QuoteResponse> quotes = service.findByClient(new ClientId(clientId));
        System.out.println(getQuoteTable(quotes));
    }

    public void findByProject() {
        final ProjectId projectId = scanProjectId();
        List<QuoteResponse> quotes = service.findByProject(projectId);
        System.out.println(getQuoteTable(quotes));
    }

    private ProjectId scanProjectId() {
        return new ProjectId(scanUuid("Enter the project ID : ", ValidationStrategies.VALID_UUID));
    }

    private QuoteId scanQuoteId(String prompt) {
        return new QuoteId(scanUuid(prompt, input -> service.existsById(new QuoteId(input))));
    }

    private String getQuoteTable(List<QuoteResponse> quotes) {
        return AsciiTable.getTable(quotes, Arrays.asList(
                new Column().with(quote -> quote.id().value().toString()),
                new Column().header("Issue Date").with(quote -> quote.issueDate().toString()),
                new Column().header("Validity Date").with(quote -> quote.validityDate().toString()),
                new Column().header("Accepted").with(quote -> quote.accepted() ? "Yes" : "No"),
                new Column().header("Created At").with(client -> client.createdAt().toString()),
                new Column().header("Last Updated At").with(client -> client.updatedAt() != null ? client.updatedAt().toString() : "Not Updated Yet")
        ));
    }

    public void setMenuUi(MainMenuUi menuUi) {
        this.menuUi = menuUi;
    }
}
