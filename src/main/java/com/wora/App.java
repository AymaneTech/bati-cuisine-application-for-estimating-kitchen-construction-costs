package com.wora;

import com.wora.client.application.mapper.ClientMapper;
import com.wora.client.application.service.ClientService;
import com.wora.client.application.service.impl.ClientServiceImpl;
import com.wora.client.domain.repository.ClientRepository;
import com.wora.client.infrastructure.mapper.ClientResultSetMapper;
import com.wora.client.infrastructure.persistence.ClientRepositoryImpl;
import com.wora.client.infrastructure.presentation.ClientUi;
import com.wora.component.application.dto.request.MaterielRequest;
import com.wora.component.application.dto.response.MaterielResponse;
import com.wora.component.application.mapper.MaterielMapper;
import com.wora.component.application.services.ComponentService;
import com.wora.component.application.services.impl.MaterielServiceImpl;
import com.wora.component.domain.entity.Materiel;
import com.wora.component.domain.repository.ComponentRepository;
import com.wora.component.infrastructure.mapper.MaterielResultSetMapper;
import com.wora.component.infrastructure.presentation.MaterielUi;
import com.wora.component.infrastructure.presistence.MaterielRepositoryImpl;
import com.wora.project.infrastructure.mapper.ProjectResultSetMapper;
import com.wora.project.valueObject.ProjectId;

import java.util.UUID;

public class App {
    public static void main(String[] args) {
        final ClientRepository clientRepository = new ClientRepositoryImpl(new ClientResultSetMapper());
        final ClientService clientService = new ClientServiceImpl(clientRepository, new ClientMapper());
        final ClientUi clientUi = new ClientUi(clientService);

        final MaterielResultSetMapper resultSetMapper = new MaterielResultSetMapper(new ProjectResultSetMapper(new ClientResultSetMapper()));
        final ComponentRepository<Materiel> materielRepository = new MaterielRepositoryImpl(resultSetMapper);
        final ComponentService<MaterielRequest, MaterielResponse> materielService = new MaterielServiceImpl(materielRepository, new MaterielMapper());
        final MaterielUi materielUi = new MaterielUi(materielService);

        materielUi.create(new ProjectId(UUID.fromString("8ff91dff-fe1c-4273-b555-2468dd1d6aeb")));

    }
}
