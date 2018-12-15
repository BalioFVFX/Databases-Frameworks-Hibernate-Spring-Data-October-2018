package alararestaurant.service;

import alararestaurant.domain.entities.Category;
import alararestaurant.domain.entities.Item;
import alararestaurant.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public String exportCategoriesByCountOfItems() {
        StringBuilder output = new StringBuilder();
        List<Category> categories = this.categoryRepository.query1();

        for (Category category : categories) {
            output.append(String.format("Category: %s", category.getName())).append(System.lineSeparator());
            for (Item item : category.getItems()) {
                output.append(String.format("--- Item name: %s", item.getName())).append(System.lineSeparator());
                output.append(String.format("--- Item Price: %s", item.getPrice())).append(System.lineSeparator());
                output.append(System.lineSeparator());
            }
        }
        return output.toString();
    }
}
