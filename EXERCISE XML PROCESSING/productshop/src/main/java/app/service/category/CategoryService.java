package app.service.category;

import app.domain.dtos.output.query3.CategoriesByProductsCountListDto;
import app.domain.dtos.seeders.CategorySeederDto;

import java.util.List;

public interface CategoryService {
    void saveAll(List<CategorySeederDto> categories);
    CategoriesByProductsCountListDto categoriesByProductsCount();
}
