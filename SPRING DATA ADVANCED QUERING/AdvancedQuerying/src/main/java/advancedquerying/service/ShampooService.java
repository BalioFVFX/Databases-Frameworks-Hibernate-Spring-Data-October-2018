package advancedquerying.service;

import advancedquerying.domain.entities.Ingredient;
import advancedquerying.domain.entities.Shampoo;
import advancedquerying.domain.entities.Size;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface ShampooService {
    List<String> findAllBySizeOrderedById(String size);
    List<String> findAllBySizeOrLabel_IdOrderByPriceAsc(String size, Long labelId);
    List<String> findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal price);
    Long countAllByPriceLessThan(BigDecimal price);
    List<String> selectShampoosByIngredients(Set<String> ingredientNames);
    List<String> selectShampoosByIngredientsCount(Long count);
    BigDecimal selectIngredientNameAndShampooBrandByName(String name);
}
