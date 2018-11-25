package app.repository;

import app.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "SELECT c.name as category, COUNT(c.id) as productsCount, AVG(p.price) as averagePrice, SUM(p.price) as totalRevenue FROM category_products as cp join categories as c on c.id = cp.category_id\n" +
            "join products as p \n" +
            "on p.id = cp.product_id\n" +
            "group by c.id", nativeQuery = true)
    List<Object[]> query3();
}
