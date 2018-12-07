package app.service.impl;

import app.entity.Branch;
import app.entity.Employee;
import app.entity.EmployeeCard;
import app.entity.dto.exportjson.ProductiveEmployeeJsonDto;
import app.entity.dto.importxml.EmployeeXmlDto;
import app.entity.dto.importxml.EmployeeXmlListDto;
import app.mappers.File.FileMapper;
import app.mappers.json.JsonMapper;
import app.mappers.modelmapper.CMapper;
import app.mappers.xml.XmlMapper;
import app.repository.BranchRepository;
import app.repository.EmployeeCardRepository;
import app.repository.EmployeeRepository;
import app.service.EmployeeService;
import app.validator.CValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final static String EMPLOYEE_XML_FILE_PATH = "C:\\Users\\Erik\\Desktop\\Hiberspring Inc\\src\\main\\resources\\xml\\input\\employees.xml";
    private final static String PRODUCTIVE_EMPLOYEES_JSON_FILE_PATH = "C:\\Users\\Erik\\Desktop\\Hiberspring Inc\\src\\main\\resources\\json\\output\\productive-employees.json";

    private final EmployeeRepository employeeRepository;
    private final EmployeeCardRepository employeeCardRepository;
    private final BranchRepository branchRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeCardRepository employeeCardRepository, BranchRepository branchRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeCardRepository = employeeCardRepository;
        this.branchRepository = branchRepository;
    }

    @Override
    public void saveAll() throws JAXBException {
        StringBuilder output = new StringBuilder();
        EmployeeXmlListDto employeeXmlListDtos = XmlMapper.unmarshall(new File(EMPLOYEE_XML_FILE_PATH), EmployeeXmlListDto.class);

        for (EmployeeXmlDto employeeXmlDto : employeeXmlListDtos.getEmployees()) {
            if(!CValidator.isValid(employeeXmlDto)){
                output.append("Error: Invalid data").append(System.lineSeparator());
                continue;
            }
            EmployeeCard employeeCardEntity = this.employeeCardRepository.findByNumber(employeeXmlDto.getCard()).orElse(null);

            if(employeeCardEntity == null){
                output.append("Error: Invalid data").append(System.lineSeparator());
                continue;
            }

            Employee employeeEntity = this.employeeRepository.findByCard(employeeCardEntity).orElse(null);

            if(employeeEntity != null){
                output.append("Error: Invalid data").append(System.lineSeparator());
                continue;
            }

            Branch branchEntity = this.branchRepository.findByName(employeeXmlDto.getBarnch()).orElse(null);

            if(branchEntity == null){
                output.append("Error: Invalid data").append(System.lineSeparator());
                continue;
            }

            Employee employeeEntityToImport = CMapper.mapper().map(employeeXmlDto, Employee.class);
            employeeEntityToImport.setBranch(branchEntity);
            employeeEntityToImport.setCard(employeeCardEntity);
            this.employeeRepository.saveAndFlush(employeeEntityToImport);
            output.append(String.format("Successfully imported  %s %s %s", "Employee", employeeEntityToImport.getFirstName(), employeeEntityToImport.getLastName())).append(System.lineSeparator());
        }
        System.out.println(output.toString());

    }

    @Override
    public void saveProductiveEmployees() throws IOException {
        List<Employee> productiveEmployeeEntities = this.employeeRepository.productiveEmployees();
        List<ProductiveEmployeeJsonDto> productiveEmployeeJsonDtos = new ArrayList<>();
        for (Employee productiveEmployeeEntity : productiveEmployeeEntities) {
            ProductiveEmployeeJsonDto productiveEmployeeJsonDto = CMapper.mapper().map(productiveEmployeeEntity, ProductiveEmployeeJsonDto.class);
            productiveEmployeeJsonDto.setFullName(productiveEmployeeEntity.getFirstName() + " " + productiveEmployeeEntity.getLastName());
            productiveEmployeeJsonDto.setNumber(productiveEmployeeEntity.getCard().getNumber());
            productiveEmployeeJsonDtos.add(productiveEmployeeJsonDto);
        }
        String jsonStr = JsonMapper.toJsonFile(productiveEmployeeJsonDtos);
        FileMapper.writeFile(PRODUCTIVE_EMPLOYEES_JSON_FILE_PATH, jsonStr);
    }
}
