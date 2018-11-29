package app.repository;

import app.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT p from app.domain.entities.Product as p where p.price >= 500 and p.price <= 1000 order by p.price asc")
    List<Product> query1ProductsInRange();
}
