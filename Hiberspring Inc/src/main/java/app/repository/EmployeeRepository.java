package app.repository;

import app.entity.Employee;
import app.entity.EmployeeCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee>findByCard(EmployeeCard employeeCard);

    @Query(value = "SELECT * FROM employees as e\n" +
            "JOIN branches as b\n" +
            "on e.branch_id = b.id\n" +
            "JOIN products as p\n" +
            "on b.id = p.branch_id\n" +
            "group by e.id\n" +
            "order by CONCAT(e.first_name, ' ', e.last_name) asc, length(e.position) desc", nativeQuery = true)
    List<Employee> productiveEmployees();
}
