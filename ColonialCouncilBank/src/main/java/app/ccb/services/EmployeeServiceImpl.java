package app.ccb.services;

import app.ccb.domain.dtos.exoportdto.EmployeeExportDto;
import app.ccb.domain.dtos.importjson.EmployeeJsonDto;
import app.ccb.domain.entities.Branch;
import app.ccb.domain.entities.Employee;
import app.ccb.repositories.BranchRepository;
import app.ccb.repositories.EmployeeRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final static String EMPLOYEE_JSON_FILE_PATH = "C:\\Users\\Erik\\Desktop\\ColonialCouncilBank-skeleton\\ColonialCouncilBank\\src\\main\\resources\\files\\json\\employees.json";

    private final EmployeeRepository employeeRepository;
    private final BranchRepository branchRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, BranchRepository branchRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil, FileUtil fileUtil) {
        this.employeeRepository = employeeRepository;
        this.branchRepository = branchRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
    }

    @Override
    public Boolean employeesAreImported() {
        return this.employeeRepository.count() != 0;
    }

    @Override
    public String readEmployeesJsonFile() throws IOException {
        String readedJsonStr = fileUtil.readFile(EMPLOYEE_JSON_FILE_PATH);
        return readedJsonStr;
    }

    @Override
    public String importEmployees(String employees) {
        StringBuilder output = new StringBuilder();
        EmployeeJsonDto[] employeesJsonDto = this.gson.fromJson(employees, EmployeeJsonDto[].class);
        for (EmployeeJsonDto employeeJsonDto : employeesJsonDto) {

            if(validationUtil.isValid(employeeJsonDto) == false){
                output.append("Error: Incorrect Data!");
                output.append(System.lineSeparator());
                continue;
            }

            Branch branchEntity = this.branchRepository.findByName(employeeJsonDto.getBranchName()).orElse(null);

            if(branchEntity == null){
                output.append("Error: Incorrect Data!");
                output.append(System.lineSeparator());
                continue;
            }

            if(this.employeeRepository.findByFirstNameAndLastName(employeeJsonDto.getFullName().split("\\s+")[0],
                    employeeJsonDto.getFullName().split("\\s+")[1]).orElse(null) != null){
                output.append("Error: Incorrect Data!");
                output.append(System.lineSeparator());
                continue;
            }

            Employee employeeEntity = modelMapper.map(employeeJsonDto, Employee.class);
            employeeEntity.setBranch(branchEntity);
            employeeEntity.setStartedOn(LocalDate.parse(employeeJsonDto.getStartedOn()));
            employeeEntity.setFirstName(employeeJsonDto.getFullName().split("\\s+")[0]);
            employeeEntity.setLastName(employeeJsonDto.getFullName().split("\\s+")[1]);


            this.employeeRepository.saveAndFlush(employeeEntity);
            output.append(String.format("Successfully imported Employee - %s %s", employeeEntity.getFirstName(), employeeEntity.getLastName()));
            output.append(System.lineSeparator());
        }
        return output.toString();
    }

    @Override
    public String exportTopEmployees() {
        StringBuilder output = new StringBuilder();
        List<Employee> employeeEntities = this.employeeRepository.topEmployees();
        for (Employee employeeEntity: employeeEntities){
            EmployeeExportDto exportDto = modelMapper.map(employeeEntity, EmployeeExportDto.class);
            exportDto.setFullName(employeeEntity.getFirstName() + " " + employeeEntity.getLastName());
            output.append(exportDto.toString()).append(System.lineSeparator());
        }
        return output.toString();
    }
}
