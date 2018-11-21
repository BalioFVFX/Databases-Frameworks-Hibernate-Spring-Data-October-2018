package app;

import app.dtos.EmployeeDto;
import app.dtos.ManagerDto;
import app.entity.Employee;
import app.services.EmployeeService;
import com.sun.prism.impl.ps.BaseShaderContext;
import org.modelmapper.*;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class AppRunner implements CommandLineRunner {

    final private EmployeeService employeeService;

    @Autowired
    public AppRunner(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {

        ModelMapper mapper = new ModelMapper();


        // Task 01  Create an instance of employee object and
        // use the ModelMapper to map the newly created
        // EmployeeDto to object of type EmployeeDto.

//        Employee employee = new Employee("Pesho", "Peshev", new BigDecimal(1300), LocalDate.now(), "Peshov Street 31");
//        EmployeeDto employeeDto = mapper.map(employee, EmployeeDto.class);
//
//        System.out.println("Employee dto first name - " + employeeDto.getFirstName());
//        System.out.println("Employee dto last name - " + employeeDto.getLastName());
//        System.out.println("Employee dto salary - " + employeeDto.getSalary());

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        mapper.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
        // Task 02

        Employee emp1 = this.employeeService.findById(1L);
        Employee emp2 = this.employeeService.findById(2L);

        ManagerDto mDto = new ManagerDto();
        List<Employee> empList = new ArrayList<>();
        empList.add(emp1);
        empList.add(emp2);
        List<ManagerDto> managers = new ArrayList<>();


        java.lang.reflect.Type targetListType = new TypeToken<List<ManagerDto>>() {}.getType();
        java.lang.reflect.Type targetListType2 = new TypeToken<List<EmployeeDto>>() {}.getType();

        managers = mapper.map(empList, targetListType);


       managers.forEach(m -> {
           System.out.println(m.getFirstName() + m.getLastName() + " | Employees: " + m.getEmpChargeSize());
           m.getEmpCharge().forEach(e -> {
               System.out.println("- " + e.getFirstName() + e.getLastName() + e.getSalary());
           });
       });


       // Task 03

        List<EmployeeDto> empDtos = mapper.map(this.employeeService.findAllBefore1990(), targetListType2);

        empDtos.forEach(System.out::println);
    }
}
