package advancedquerying.service;

import advancedquerying.domain.entities.Ingredient;
import advancedquerying.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class IngredientServiceImpl implements IngredientService{

    final private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<String> findAllIngredientsStartingWith(String str) {
        return this.ingredientRepository.findAllByNameStartingWith(str)
                .stream()
                .map(Ingredient::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllByNameInOrderByPriceAsc(List<String> names) {
        return this.ingredientRepository.findAllByNameInOrderByPriceAsc(names)
                .stream()
                .map(Ingredient::getName)
                .collect(Collectors.toList());
    }

    @Override
    public Set<String> findAllByNameIn(Set<String> names) {
        return this.ingredientRepository.findAllByNameIn(names).stream()
                .map(Ingredient::getName)
                .collect(Collectors.toSet());
    }

    @Override
    public Integer deleteIngredientsByName(List<String> names) {
        return this.ingredientRepository.deleteIngredientsByName(names);
    }

    @Override
    public Integer updateIngredientsByPrice() {
        return this.ingredientRepository.updateIngredientsByPrice();
    }

    @Override
    public Integer updateIngredientsByNames(List<String> names, BigDecimal price) {
        return this.ingredientRepository.updateIngredientsByNames(names, price);
    }
}
