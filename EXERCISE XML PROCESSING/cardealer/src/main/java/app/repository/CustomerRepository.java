package app.repository;

import app.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value = "select * from customers as c order by c.date_of_birth asc, c.is_young_driver desc;", nativeQuery = true)
    List<Customer> query1();

}
