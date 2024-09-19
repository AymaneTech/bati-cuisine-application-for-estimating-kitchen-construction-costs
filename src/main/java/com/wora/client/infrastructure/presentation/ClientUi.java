package com.wora.client.infrastructure.presentation;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.wora.client.application.dto.request.ClientRequest;
import com.wora.client.application.dto.response.ClientResponse;
import com.wora.client.application.service.ClientService;
import com.wora.client.domain.valueObject.ClientId;
import com.wora.client.domain.valueObject.Name;
import com.wora.common.util.ValidationStrategies;
import com.wora.mainMenu.MainMenuUi;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.wora.common.util.InputScanner.*;
import static com.wora.common.util.Print.secondaryTitle;
import static com.wora.common.util.Print.title;

public class ClientUi {
    private final ClientService service;
    private MainMenuUi menuUi;

    public ClientUi(ClientService service) {
        this.service = service;
    }

    public void showMenu() {
        title("Welcome to client menu");
        System.out.println("1. Create new client");
        System.out.println("2. Update an existing client");
        System.out.println("3. Delete an existing client");
        System.out.println("4. Show all clients");
        System.out.println("5. Show client by id");
        System.out.println("6. Back to main menu.");
        System.out.println("0. exit.");

        Integer userChoice = scanInt("Please to enter you choice: ", ValidationStrategies.POSITIVE_INT);

        switch (userChoice) {
            case 1 -> this.createAndDisplay();
            case 2 -> this.update();
            case 3 -> this.delete();
            case 4 -> this.showAll();
            case 5 -> this.showById();
            case 6 -> menuUi.showMenu();
            case 0 -> System.exit(0);
            default -> throw new IllegalArgumentException("Invalid choice");
        }
    }

    public void createAndDisplay() {
        System.out.println(getTable(List.of(create())));
        this.showMenu();
    }

    private ClientResponse create() {
        secondaryTitle("Please to enter all necessary information!");
        final String firstName = scanString("Please to enter the client first name: ", ValidationStrategies.NOT_BLANK);
        final String lastName = scanString("Please to enter the client last name: ", ValidationStrategies.NOT_BLANK);
        final String phone = scanString("Please to enter the client phone: ", ValidationStrategies.combine(
                ValidationStrategies.VALID_PHONE,
                input -> !service.existsByPhone(input)
        ));
        final String address = scanString("Please to enter your address: ", ValidationStrategies.combine(
                ValidationStrategies.NOT_BLANK,
                input -> !service.existsByAddress(input)
        ));
        final Boolean isProfessional = scanBoolean("is he professional (y/n): ");

        return service.create(new ClientRequest(new Name(firstName, lastName), phone, address, isProfessional));
    }

    private ClientId createForProject() {
        return create().id();
    }

    public void update() {
        secondaryTitle("Please to enter all necessary information!: ");
        final UUID id = scanUuid("Please to enter the client id: ",
                input -> service.existsById(new ClientId(input))
        );

        final ClientResponse existingClient = service.findById(new ClientId(id));

        final String firstName = scanString("please to enter the client first name ( " + existingClient.name().firstName() + " )", ValidationStrategies.NOT_BLANK);
        final String lastName = scanString("Please to enter the client last name:  ( " + existingClient.name().lastName() + ")", ValidationStrategies.NOT_BLANK);
        final String phone = scanString("Please to enter the client phone (" + existingClient.phone() + "):", ValidationStrategies.combine(
                ValidationStrategies.VALID_PHONE,
                input -> !service.existsByPhone(input)
        ));
        final String address = scanString("Please to enter the client address ( " + existingClient.address() + "): ", ValidationStrategies.combine(
                ValidationStrategies.NOT_BLANK,
                input -> !service.existsByAddress(input)
        ));
        final Boolean isProfessional = scanBoolean("is he professional (y/n): ");

        final ClientResponse clientResponse = service.update(new ClientId(id), new ClientRequest(new Name(firstName, lastName), phone, address, isProfessional));
        System.out.println(getTable(List.of(clientResponse)));
        this.showMenu();
    }

    public void delete() {
        secondaryTitle("Please to follow instructions to delete a client!");
        final UUID id = scanUuid("Please enter the client id to delete",
                input -> service.existsById(new ClientId(input))
        );

        service.delete(new ClientId(id));
        System.out.println("the client deleted successfully!");
        this.showMenu();
    }

    public void showAll() {
        final List<ClientResponse> clients = service.findAll();
        System.out.println(getTable(clients));
        this.showMenu();
    }

    public void showById() {
        final UUID id = scanUuid("Please to enter the client id : ",
                input -> service.existsById(new ClientId(input))
        );
        System.out.println(getTable(
                List.of(
                        service.findById(new ClientId(id))
                )
        ));
        this.showMenu();
    }

    public ClientId searchOrCreate() {
        System.out.println("--- client searching !!");
        System.out.println("do you wish to look for existing client or create new one:");
        System.out.println("1. search for existing client.");
        System.out.println("2. create new client.\n");

        final Integer userChoice = scanInt("Please enter you choice", ValidationStrategies.POSITIVE_INT);

        return switch (userChoice) {
            case 1 -> findById();
            case 2 -> createForProject();
            default -> throw new IllegalStateException("Unexpected value: " + userChoice);
        };
    }

    private ClientId findById() {
        final UUID id = scanUuid("Please to enter the client id : ",
                input -> service.existsById(new ClientId(input))
        );
        System.out.println("Client found successfully!");
        return new ClientId(id);
    }

    private String getTable(List<ClientResponse> clients) {
        return AsciiTable.getTable(clients, Arrays.asList(
                new Column().with(client -> client.id().value().toString()),
                new Column().header("First Name").with(client -> client.name().firstName()),
                new Column().header("Last Name").with(client -> client.name().lastName()),
                new Column().header("Phone").with(ClientResponse::phone),
                new Column().header("Address").with(ClientResponse::address),
                new Column().header("Is Professional").with(client -> client.isProfessional() ? "Yes" : "No"),
                new Column().header("Created At").with(client -> client.createdAt().toString()),
                new Column().header("Last Updated At").with(client -> client.updatedAt() != null ? client.updatedAt().toString() : "Not Updated Yet")
        ));
    }

    public void setMenuUi(MainMenuUi mainMenuUi) {
        this.menuUi = mainMenuUi;
    }
}
