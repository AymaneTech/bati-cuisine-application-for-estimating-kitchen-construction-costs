package com.wora;

import com.wora.client.application.mapper.ClientMapper;
import com.wora.client.application.service.ClientService;
import com.wora.client.application.service.impl.ClientServiceImpl;
import com.wora.client.domain.repository.ClientRepository;
import com.wora.client.infrastructure.mapper.ClientResultSetMapper;
import com.wora.client.infrastructure.persistence.ClientRepositoryImpl;
import com.wora.client.infrastructure.presentation.ClientUi;

public class App {
    public static void main(String[] args) {
        final ClientRepository clientRepository = new ClientRepositoryImpl(new ClientResultSetMapper());
        final ClientService clientService = new ClientServiceImpl(clientRepository, new ClientMapper());
        final ClientUi clientUi = new ClientUi(clientService);

        clientUi.showMenu();
    }
}
