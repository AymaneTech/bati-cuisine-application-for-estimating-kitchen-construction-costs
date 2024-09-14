package com.wora.client.infrastructure.presentation;

import com.wora.client.application.dtos.requests.ClientRequest;
import com.wora.client.application.dtos.responses.ClientResponse;
import com.wora.client.application.services.ClientService;
import com.wora.client.domain.valueObjects.Name;
import com.wora.common.utils.ValidationStrategies;

import static com.wora.common.utils.InputScanner.scanInt;
import static com.wora.common.utils.InputScanner.scanString;
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
            case 2 -> System.out.println("hello world ");
            default -> throw new IllegalArgumentException("Invalid choice");
        }
    }

    public void create() {
        secondaryTitle("Please to enter all necessary information!");
        final String firstName = scanString("Please to enter the client first name: ", ValidationStrategies.NOT_BLANK);
        final String lastName = scanString("Please to enter the client last name: ", ValidationStrategies.NOT_BLANK);
        final String phone = scanString("Please to enter the client phone: ", ValidationStrategies.VALID_PHONE);
        final String address = scanString("Please to enter your address", ValidationStrategies.NOT_BLANK);
        // TODO: add the boolean scanner
        final Boolean isProfessional = true;

        final ClientRequest clientRequest = new ClientRequest(new Name(firstName, lastName), phone, address, isProfessional);
        ClientResponse clientResponse = service.create(clientRequest);


    }

}
