package app.repository;

import app.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("select e FROM Employee as e where YEAR(e.birthday) < 1990")
    List<Employee> findAllBefore1990();
}
