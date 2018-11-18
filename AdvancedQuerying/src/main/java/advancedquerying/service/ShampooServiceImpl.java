package advancedquerying.service;

import advancedquerying.domain.entities.Ingredient;
import advancedquerying.domain.entities.Size;
import advancedquerying.repository.IngredientRepository;
import advancedquerying.repository.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ShampooServiceImpl implements ShampooService{

    private final ShampooRepository shampooRepository;
    private final IngredientRepository ingredientRepository;

    @Autowired
    public ShampooServiceImpl(ShampooRepository shampooRepository, IngredientRepository ingredientRepository) {
        this.shampooRepository = shampooRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<String> findAllBySizeOrderedById(String size) {
        return this.shampooRepository.findAllBySizeOrderById(Size.valueOf(size.toUpperCase()))
                .stream().map(s -> String.format("%s %s %.2f",
                        s.getBrand(), s.getSize().name(), s.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBySizeOrLabel_IdOrderByPriceAsc(String size, Long labelId) {
        return this.shampooRepository.findAllBySizeOrLabel_IdOrderByPriceAsc(
                Size.valueOf(size.toUpperCase()), labelId
        ).stream().map(s -> String.format("%s %s %.2f",
                s.getBrand(), s.getSize().name(), s.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal price) {
        return this.shampooRepository.findAllByPriceGreaterThanOrderByPriceDesc(price)
                .stream()
                .map(s -> String.format("%s %s %.2flv.",
                        s.getBrand(),
                        s.getSize().name(),
                        s.getPrice())).collect(Collectors.toList());
    }

    @Override
    public Long countAllByPriceLessThan(BigDecimal price) {
        return this.shampooRepository.countAllByPriceLessThan(price);
    }

    @Override
    public List<String> selectShampoosByIngredients(Set<String> ingredientNames) {
        Set<Ingredient> ingredients = this.ingredientRepository.findAllByNameIn(ingredientNames);


        return this.shampooRepository.selectShampoosByIngredients(ingredients)
        .stream()
        .map(s -> s.getBrand())
        .collect(Collectors.toList());
    }

    @Override
    public List<String> selectShampoosByIngredientsCount(Long count) {
        return this.shampooRepository.selectShampoosByIngredientsCount(count)
                .stream()
                .map(s -> s.getBrand())
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal selectIngredientNameAndShampooBrandByName(String name) {
        return this.shampooRepository.selectIngredientNameAndShampooBrandByName(name);
    }
}
