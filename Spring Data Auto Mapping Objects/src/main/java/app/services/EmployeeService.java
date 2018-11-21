package app.services;

import app.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    void persist(Employee employee);
    Employee findById(Long id);
    List<Employee> findAllBefore1990();
}
