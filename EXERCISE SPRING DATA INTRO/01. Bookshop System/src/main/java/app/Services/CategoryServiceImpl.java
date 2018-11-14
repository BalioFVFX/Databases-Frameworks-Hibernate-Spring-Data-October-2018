package app.Services;

import app.Entities.Category;
import app.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void persistCategory(Category category) {
        this.categoryRepository.save(category);
    }

    @Override
    public void persistCategories(List<Category> categories) {
        this.categoryRepository.saveAll(categories);
    }

    @Override
    public List<Category> getAllCategories() {
        Iterable<Category> authors = this.categoryRepository.findAll();
        List<Category> categoryList = new ArrayList<Category>();
        authors.forEach(categoryList::add);
        return categoryList;
    }
}
