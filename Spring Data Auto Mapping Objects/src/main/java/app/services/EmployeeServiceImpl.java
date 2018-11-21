package app.services;

import app.entity.Employee;
import app.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    final private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void persist(Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    public Employee findById(Long id) {
        return this.employeeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Employee> findAllBefore1990() {
        return this.employeeRepository.findAllBefore1990();
    }
}
