package com.wora.mainMenu;

import com.wora.client.infrastructure.presentation.ClientUi;
import com.wora.common.util.ValidationStrategies;
import com.wora.project.infrastructure.presentation.ProjectUi;
import com.wora.quotation.infrastructure.presentation.QuoteUi;

import static com.wora.common.util.InputScanner.scanInt;

public class MainMenuUi {
    private final ClientUi clientUi;
    private final ProjectUi projectUi;
    private final QuoteUi quoteUi;

    public MainMenuUi(ClientUi clientUi, ProjectUi projectUi, QuoteUi quoteUi) {
        this.clientUi = clientUi;
        this.projectUi = projectUi;
        this.quoteUi = quoteUi;
    }

    public void showMenu() {
        System.out.println(applicationTitle());
        while (true) {
            System.out.println("--------------- main menu --------------");
            System.out.println("1. Manage Clients.");
            System.out.println("2. Manage Projects.");
            System.out.println("3. Manage Quotations.");
            System.out.println("0. Exit.");

            final int userChoice = scanInt("-- Please to enter your choice: ", ValidationStrategies.POSITIVE_INT);

            switch (userChoice) {
                case 1 -> clientUi.showMenu();
                case 2 -> projectUi.showMenu();
                case 3 -> quoteUi.showMenu();
                case 0 -> System.exit(0);
                default -> throw new IllegalStateException("Invalid choice");
            }
        }
    }

    private String applicationTitle() {
        return " _______               __      __         ______             __            __                     \n" +
                "|       \\             |  \\    |  \\       /      \\           |  \\          |  \\                    \n" +
                "| $$$$$$$\\  ______   _| $$_    \\$$      |  $$$$$$\\ __    __  \\$$  _______  \\$$ _______    ______  \n" +
                "| $$__/ $$ |      \\ |   $$ \\  |  \\      | $$   \\$$|  \\  |  \\|  \\ /       \\|  \\|       \\  /      \\ \n" +
                "| $$    $$  \\$$$$$$\\ \\$$$$$$  | $$      | $$      | $$  | $$| $$|  $$$$$$$| $$| $$$$$$$\\|  $$$$$$\\\n" +
                "| $$$$$$$\\ /      $$  | $$ __ | $$      | $$   __ | $$  | $$| $$ \\$$    \\ | $$| $$  | $$| $$    $$\n" +
                "| $$__/ $$|  $$$$$$$  | $$|  \\| $$      | $$__/  \\| $$__/ $$| $$ _\\$$$$$$\\| $$| $$  | $$| $$$$$$$$\n" +
                "| $$    $$ \\$$    $$   \\$$  $$| $$       \\$$    $$ \\$$    $$| $$|       $$| $$| $$  | $$ \\$$     \\\n" +
                " \\$$$$$$$   \\$$$$$$$    \\$$$$  \\$$        \\$$$$$$   \\$$$$$$  \\$$ \\$$$$$$$  \\$$ \\$$   \\$$  \\$$$$$$$\n" +
                "                                                                                                  \n" +
                "                                                                                                  \n" +
                "                                                                                                  \n" +
                "\n";
    }
}
