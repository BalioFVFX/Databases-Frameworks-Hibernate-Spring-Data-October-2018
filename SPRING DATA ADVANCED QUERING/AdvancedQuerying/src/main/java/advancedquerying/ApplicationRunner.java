package advancedquerying;


import advancedquerying.service.IngredientService;
import advancedquerying.service.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@SpringBootApplication
public class ApplicationRunner implements CommandLineRunner {

    private final ShampooService shampooService;
    private final IngredientService ingredientService;

    @Autowired
    public ApplicationRunner(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... args) throws Exception {

        //shampooService.findAllBySizeOrderedById("MEDIUM").forEach(System.out::println);
        //shampooService.findAllBySizeOrLabel_IdOrderByPriceAsc("MEDIUM", 10L).forEach(System.out::println);
        //shampooService.findAllByPriceGreaterThanOrderByPriceDesc(new BigDecimal(5)).forEach(System.out::println);
        //ingredientService.findAllIngredientsStartingWith("M").forEach(System.out::println);
        List<String> names = new ArrayList<String>();
        names.add("Apple");
        names.add("Lavender");
        names.add("Herbs");
        //ingredientService.findAllByNameInOrderByPriceAsc(names).forEach(System.out::println);
        //System.out.println(shampooService.countAllByPriceLessThan(new BigDecimal(8.50)));
        Set<String> ingredientsSet = new HashSet<String>();
        ingredientsSet.add("Berry");
        ingredientsSet.add("Mineral-Collagen");
        //shampooService.selectShampoosByIngredients(ingredientsSet).forEach(System.out::println);
        //shampooService.selectShampoosByIngredientsCount(2L).forEach(System.out::println);
        //System.out.println(shampooService.selectIngredientNameAndShampooBrandByName("Silk Comb"));
        List<String> ingredientNames = new ArrayList<>();
        ingredientNames.add("Apple");
        ingredientNames.add("Nettle");
        //System.out.println(ingredientService.deleteIngredientsByName(ingredientNames) + " ingredients deleted");
        //System.out.println(ingredientService.updateIngredientsByPrice() + " ingredients prices were updated!");

        List<String> ingredientNamesToUpdatePrices = new ArrayList<>();
        ingredientNamesToUpdatePrices.add("Aloe Vera");
        ingredientNamesToUpdatePrices.add("Lavender");

        System.out.println(ingredientService.updateIngredientsByNames(ingredientNamesToUpdatePrices, new BigDecimal(100)) + " ingredients were updated with a new price");
    }
}
