package alararestaurant.service;

import alararestaurant.domain.dtos.importjson.EmployeeJsonDto;
import alararestaurant.domain.entities.Employee;
import alararestaurant.domain.entities.Position;
import alararestaurant.repository.EmployeeRepository;
import alararestaurant.repository.PositionRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmployeeServiceImpl implements EmployeeService {
        private final static String EMPLOYEES_JSON_FILE_PATH = System.getProperty("user.dir") + ("\\src\\main\\resources\\files\\employees.json");

    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final FileUtil fileUtil;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PositionRepository positionRepository, FileUtil fileUtil, ValidationUtil validationUtil, ModelMapper modelMapper, Gson gson) {
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public Boolean employeesAreImported() {
        return this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesJsonFile() throws IOException {
        return this.fileUtil.readFile(EMPLOYEES_JSON_FILE_PATH);
    }

    @Override
    public String importEmployees(String employees) {
        StringBuilder output = new StringBuilder();
        EmployeeJsonDto[] employeeJsonDtos = this.gson.fromJson(employees, EmployeeJsonDto[].class);

        for (EmployeeJsonDto employeeJsonDto : employeeJsonDtos) {
            System.out.println();


            if(!this.validationUtil.isValid(employeeJsonDto)){
                output.append("Invalid data format.").append(System.lineSeparator());
                continue;
            }

            Position positionEntityToImport = this.positionRepository.findByName(employeeJsonDto.getPosition()).orElse(null);

            if(positionEntityToImport == null){
                positionEntityToImport = new Position();
                positionEntityToImport.setName(employeeJsonDto.getPosition());
                this.positionRepository.saveAndFlush(positionEntityToImport);
            }

            Position positionEntity = this.positionRepository.findByName(employeeJsonDto.getPosition()).orElse(null);
            Employee employeeEntityToImport = this.modelMapper.map(employeeJsonDto, Employee.class);
            employeeEntityToImport.setPosition(positionEntity);
            this.employeeRepository.saveAndFlush(employeeEntityToImport);
            output.append(String.format("Record %s successfully imported.", employeeEntityToImport.getName())).append(System.lineSeparator());
        }
        return output.toString();
    }
}
