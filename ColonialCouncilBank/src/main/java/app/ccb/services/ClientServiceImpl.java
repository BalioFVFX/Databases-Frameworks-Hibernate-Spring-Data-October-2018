package app.ccb.services;

import app.ccb.domain.dtos.exoportdto.ExportQ2BankAccDto;
import app.ccb.domain.dtos.exoportdto.ExportQ2ClientDto;
import app.ccb.domain.dtos.importjson.ClientJsonDto;
import app.ccb.domain.entities.Client;
import app.ccb.domain.entities.Employee;
import app.ccb.repositories.ClientRepository;
import app.ccb.repositories.EmployeeRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final static String CLIENTS_JSON_FILE_PATH = "C:\\Users\\Erik\\Desktop\\ColonialCouncilBank-skeleton\\ColonialCouncilBank\\src\\main\\resources\\files\\json\\clients.json";

    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ModelMapper modelMapper;


    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, EmployeeRepository employeeRepository, ValidationUtil validationUtil, FileUtil fileUtil, Gson gson, ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean clientsAreImported() {
        return this.clientRepository.count() != 0;
    }

    @Override
    public String readClientsJsonFile() throws IOException {
        return this.fileUtil.readFile(CLIENTS_JSON_FILE_PATH);
    }

    @Override
    public String importClients(String clients) {
        StringBuilder output = new StringBuilder();
        ClientJsonDto[] clientJsonDtos = this.gson.fromJson(clients, ClientJsonDto[].class);

        for (ClientJsonDto clientJsonDto : clientJsonDtos) {
            if (validationUtil.isValid(clientJsonDto) == false) {
                output.append("Error: Incorrect Data").append(System.lineSeparator());
                continue;
            }

            if (this.clientRepository.findByFullName(clientJsonDto.getFirstName() + " " + clientJsonDto.getLastName()).orElse(null) != null) {
                output.append("Error: Incorrect Data").append(System.lineSeparator());
                continue;
            }

            Employee employeeEntity = this.employeeRepository.findByFirstNameAndLastName(clientJsonDto.getAppointedEmployee().split("\\s+")[0],
                    clientJsonDto.getAppointedEmployee().split("\\s+")[1]).orElse(null);

            if (employeeEntity == null) {
                output.append("Error: Incorrect Data").append(System.lineSeparator());
                continue;
            }

            Client clientEntity = modelMapper.map(clientJsonDto, Client.class);
            clientEntity.setFullName(clientJsonDto.getFirstName() + " " + clientJsonDto.getLastName());
            clientEntity.getAppointedEmployees().add(employeeEntity);

            this.clientRepository.saveAndFlush(clientEntity);
            output.append(String.format("Successfully imported Client - %s", clientEntity.getFullName())).append(System.lineSeparator());
        }
        return output.toString();
    }

    @Override
    public String exportFamilyGuy() {
        StringBuilder output = new StringBuilder();
        Client clientEntity = this.clientRepository.query2();

        ExportQ2ClientDto clientDto = this.modelMapper.map(clientEntity, ExportQ2ClientDto.class);
        output.append(clientDto.toString());

        return output.toString();
    }
}
