package com.wora.client.infrastructure.presentation;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.wora.client.application.dtos.requests.ClientRequest;
import com.wora.client.application.dtos.responses.ClientResponse;
import com.wora.client.application.services.ClientService;
import com.wora.client.domain.valueObjects.ClientId;
import com.wora.client.domain.valueObjects.Name;
import com.wora.common.utils.ValidationStrategies;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.wora.common.utils.InputScanner.*;
import static com.wora.common.utils.Print.secondaryTitle;
import static com.wora.common.utils.Print.title;

public class ClientUi {
    private final ClientService service;

    public ClientUi(ClientService service) {
        this.service = service;
    }

    public void showMenu() {
        title("Welcome to client menu");
        System.out.println("1. Create new client");
        System.out.println("2. Update an existing client");
        System.out.println("3. Delete an existing client");
        System.out.println("4. Show all clients");
        System.out.println("5. Show client by name");
        System.out.println("6 Show client by id");

        Integer userChoice = scanInt("Please to enter you choice: ", ValidationStrategies.POSITIVE_INT);

        switch (userChoice) {
            case 1 -> this.create();
            case 2 -> this.update();
            default -> throw new IllegalArgumentException("Invalid choice");
        }
    }

    public void create() {
        secondaryTitle("Please to enter all necessary information!");
        final String firstName = scanString("Please to enter the client first name: ", ValidationStrategies.NOT_BLANK);
        final String lastName = scanString("Please to enter the client last name: ", ValidationStrategies.NOT_BLANK);
        final String phone = scanString("Please to enter the client phone: ", ValidationStrategies.combine(
                ValidationStrategies.NOT_BLANK,
                input -> !service.existsByPhone(input)
        ));
        final String address = scanString("Please to enter your address: ", ValidationStrategies.combine(
                ValidationStrategies.NOT_BLANK,
                input -> !service.existsByAddress(input)
        ));
        final Boolean isProfessional = true; // TODO: add the boolean scanner

        final ClientResponse clientResponse = service.create(new ClientRequest(new Name(firstName, lastName), phone, address, isProfessional));
        System.out.println(getTable(List.of(clientResponse)));
    }

    public void update() {
        secondaryTitle("Please to enter all necessary information!: ");
        final UUID id = scanUuid("Please to enter the client id: ",
                input -> service.existsById(new ClientId((UUID) input))
        );

        final ClientResponse existingClient = service.findById(new ClientId(id));

        final String firstName = scanString("please to enter the client first name ( " + existingClient.name().firstName() + " )", ValidationStrategies.NOT_BLANK);
        final String lastName = scanString("Please to enter the client last name:  ( " + existingClient.name().lastName() + ")", ValidationStrategies.NOT_BLANK);
        final String phone = scanString("Please to enter the client phone (" + existingClient.phone() + "):", ValidationStrategies.combine(
                ValidationStrategies.NOT_BLANK,
                input -> !service.existsByPhone(input)
        ));
        final String address = scanString("Please to enter the client address ( " + existingClient.address() + "): ", ValidationStrategies.combine(
                ValidationStrategies.NOT_BLANK,
                input -> !service.existsByAddress(input)
        ));
        final Boolean isProfessional = true; // TODO: add the boolean scanner

        final ClientResponse clientResponse = service.update(new ClientId(id), new ClientRequest(new Name(firstName, lastName), phone, address, isProfessional));
        System.out.println(getTable(List.of(clientResponse)));
    }

    private String getTable(List<ClientResponse> clients) {
        return AsciiTable.getTable(clients, Arrays.asList(
                new Column().with(client -> client.id().toString()),
                new Column().header("First Name").with(client -> client.name().firstName()),
                new Column().header("Last Name").with(client -> client.name().lastName()),
                new Column().header("Phone").with(ClientResponse::phone),
                new Column().header("Address").with(ClientResponse::address),
                new Column().header("Is Professional").with(client -> client.isProfessional() ? "Yes" : "No"),
                new Column().header("Created At").with(client -> client.createdAt().toString()),
                new Column().header("Last Updated At").with(client -> client.updatedAt() != null ? client.updatedAt().toString() : "Not Updated Yet")
        ));
    }

}
