package app.repository;

import app.entity.EmployeeCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeCardRepository extends JpaRepository<EmployeeCard, Integer> {
    Optional<EmployeeCard> findByNumber(String number);

    @Query(value = "SELECT * FROM employee_cards as ec\n" +
            "where id not in (SELECT card_id FROM employees)\n" +
            "order by ec.id asc", nativeQuery = true)
    List<EmployeeCard> freeCards();
}
