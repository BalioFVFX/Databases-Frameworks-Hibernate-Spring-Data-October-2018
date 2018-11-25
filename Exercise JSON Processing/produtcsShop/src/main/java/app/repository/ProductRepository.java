package app.repository;

import app.entity.Product;
import app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceGreaterThanEqualAndPriceLessThanEqualOrderByPriceAsc(BigDecimal lowerPrice, BigDecimal greaterPrice);

    @Query(value = "SELECT u FROM app.entity.Product as p join app.entity.User as u")
    List<User> query2();
}
