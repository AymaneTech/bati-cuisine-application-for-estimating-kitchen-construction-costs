package com.wora;

import com.wora.client.application.mapper.ClientMapper;
import com.wora.client.application.service.ClientService;
import com.wora.client.application.service.impl.ClientServiceImpl;
import com.wora.client.domain.repository.ClientRepository;
import com.wora.client.infrastructure.mapper.ClientResultSetMapper;
import com.wora.client.infrastructure.persistence.ClientRepositoryImpl;
import com.wora.client.infrastructure.presentation.ClientUi;
import com.wora.component.application.dto.request.MaterielRequest;
import com.wora.component.application.dto.request.WorkerRequest;
import com.wora.component.application.dto.response.MaterielResponse;
import com.wora.component.application.dto.response.WorkerResponse;
import com.wora.component.application.mapper.MaterielMapper;
import com.wora.component.application.mapper.WorkerMapper;
import com.wora.component.application.services.ComponentService;
import com.wora.component.application.services.impl.MaterielServiceImpl;
import com.wora.component.application.services.impl.WorkerServiceImpl;
import com.wora.component.domain.entity.Materiel;
import com.wora.component.domain.entity.Worker;
import com.wora.component.domain.repository.ComponentRepository;
import com.wora.component.infrastructure.mapper.MaterielResultSetMapper;
import com.wora.component.infrastructure.mapper.WorkerResultSetMapper;
import com.wora.component.infrastructure.presentation.MaterielUi;
import com.wora.component.infrastructure.presentation.WorkerUi;
import com.wora.component.infrastructure.presistence.MaterielRepositoryImpl;
import com.wora.component.infrastructure.presistence.WorkerRepositoryImpl;
import com.wora.project.application.mapper.ProjectMapper;
import com.wora.project.application.service.ProjectService;
import com.wora.project.application.service.impl.ProjectServiceImpl;
import com.wora.project.domain.repository.ProjectRepository;
import com.wora.project.infrastructure.mapper.ProjectResultSetMapper;
import com.wora.project.infrastructure.persistence.ProjectRepositoryImpl;
import com.wora.project.infrastructure.presentation.ProjectUi;

public class App {
    public static void main(String[] args) {
        final ClientRepository clientRepository = new ClientRepositoryImpl(new ClientResultSetMapper());
        final ClientService clientService = new ClientServiceImpl(clientRepository, new ClientMapper());
        final ClientUi clientUi = new ClientUi(clientService);

        final MaterielResultSetMapper resultSetMapper = new MaterielResultSetMapper();
        final ComponentRepository<Materiel> materielRepository = new MaterielRepositoryImpl(resultSetMapper);
        final ComponentService<MaterielRequest, MaterielResponse> materielService = new MaterielServiceImpl(materielRepository, new MaterielMapper());
        final MaterielUi materielUi = new MaterielUi(materielService);

        final ComponentRepository<Worker> workerRepository = new WorkerRepositoryImpl(new WorkerResultSetMapper());
        final ComponentService<WorkerRequest, WorkerResponse> workerService = new WorkerServiceImpl(workerRepository, new WorkerMapper());
        final WorkerUi workerUi = new WorkerUi(workerService);

        final ProjectRepository projectRepository = new ProjectRepositoryImpl(new ProjectResultSetMapper(new ClientResultSetMapper()));
        final ProjectService projectService = new ProjectServiceImpl(projectRepository, new ProjectMapper());
        final ProjectUi projectUi = new ProjectUi(projectService, null, clientUi, materielUi, workerUi);

        projectUi.create();
        
    }
}
