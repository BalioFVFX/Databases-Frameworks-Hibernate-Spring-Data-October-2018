package app.repository;

import app.domain.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    @Query(value = "select c from app.domain.entity.Car as c where c.make = 'Toyota' order by c.model asc, c.travelledDistance desc")
    List<Car> query2();
}
