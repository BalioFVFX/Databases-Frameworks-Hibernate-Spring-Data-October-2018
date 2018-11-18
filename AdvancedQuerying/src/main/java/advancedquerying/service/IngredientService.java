package advancedquerying.service;

import advancedquerying.domain.entities.Ingredient;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface IngredientService {
    List<String> findAllIngredientsStartingWith(String str);
    List<String> findAllByNameInOrderByPriceAsc(List<String> names);
    Set<String> findAllByNameIn(Set<String> names);
    Integer deleteIngredientsByName(List<String> names);
    Integer updateIngredientsByPrice();
    Integer updateIngredientsByNames(List<String> names, BigDecimal price);
}
