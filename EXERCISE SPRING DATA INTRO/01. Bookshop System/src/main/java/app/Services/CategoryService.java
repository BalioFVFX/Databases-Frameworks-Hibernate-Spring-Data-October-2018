package app.Services;

import app.Entities.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    void persistCategory(Category category);
    void persistCategories(List<Category> categories);
    List<Category> getAllCategories();
}
