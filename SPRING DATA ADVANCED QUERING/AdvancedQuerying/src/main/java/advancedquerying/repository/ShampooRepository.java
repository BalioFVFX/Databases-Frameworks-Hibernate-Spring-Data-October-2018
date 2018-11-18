package advancedquerying.repository;

import advancedquerying.domain.entities.Ingredient;
import advancedquerying.domain.entities.Shampoo;
import advancedquerying.domain.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {
    List<Shampoo> findAllBySizeOrderById(Size size);
    List<Shampoo> findAllBySizeOrLabel_IdOrderByPriceAsc(Size size, Long labelId);
    List<Shampoo> findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal price);
    Long countAllByPriceLessThan(BigDecimal price);

    @Query("SELECT s FROM advancedquerying.domain.entities.Shampoo AS s inner join s.ingredients as i WHERE i in :ingredients")
    List<Shampoo> selectShampoosByIngredients(@Param(value = "ingredients") Set<Ingredient> ingredients);

    @Query("SELECT s FROM advancedquerying.domain.entities.Shampoo AS s left join s.ingredients as i group by s having count(s) < :count")
    List<Shampoo> selectShampoosByIngredientsCount(@Param(value = "count") Long count);

    @Query("SELECT SUM(i.price) FROM advancedquerying.domain.entities.Shampoo AS s left join s.ingredients as i WHERE s.brand = :brand group by s")
    BigDecimal selectIngredientNameAndShampooBrandByName(@Param(value = "brand") String name);
}
