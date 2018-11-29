package app.repository;

import app.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "SELECT c.name name, COUNT(p.id) productsCount, AVG(p.price) avgPrice, SUM(p.price) totalPrice FROM categories as c\n" +
            "join category_products as cp\n" +
            "on c.id = cp.category_id\n" +
            "join products as p\n" +
            "on cp.product_id = p.id\n" +
            "GROUP BY c.id\n", nativeQuery = true)
    List<Object[]> categoriesByProductCountQuery3();
}
