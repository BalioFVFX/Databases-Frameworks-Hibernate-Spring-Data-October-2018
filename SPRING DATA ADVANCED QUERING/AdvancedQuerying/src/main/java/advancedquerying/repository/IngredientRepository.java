package advancedquerying.repository;

import advancedquerying.domain.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findAllByNameStartingWith(String startStr);
    List<Ingredient> findAllByNameInOrderByPriceAsc(List<String> names);
    Set<Ingredient> findAllByNameIn(Set<String> names);


    @Transactional
    @Modifying
    @Query(value = "UPDATE  advancedquerying.domain.entities.Ingredient AS i set i.price = :price WHERE i.name IN :names")
    Integer updateIngredientsByNames(@Param(value = "names") List<String> names, @Param(value = "price") BigDecimal price);


    @Transactional
    @Modifying
    @Query(value = "UPDATE advancedquerying.domain.entities.Ingredient AS i set i.price = i.price + (i.price * 0.10)")
    Integer updateIngredientsByPrice();


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM advancedquerying.domain.entities.Ingredient as i where i.name in :names")
    Integer deleteIngredientsByName(@Param(value = "names") List<String> names);

}
