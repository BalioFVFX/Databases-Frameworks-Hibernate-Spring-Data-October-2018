package app.service.category;

import app.domain.dto.importjson.category.CategoryJsonDto;
import app.domain.entity.Category;
import app.mapperconfig.CMapper;
import app.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void saveJson(List<CategoryJsonDto> categoryJsonDtoList) {
        for (CategoryJsonDto categoryJsonDto : categoryJsonDtoList) {
            Category entity = CMapper.mapper().map(categoryJsonDto, Category.class);
            List<Category> subCategories = entity.getSubcategories();
            saveParnet(entity, subCategories);
        }
    }

    private void saveParnet(Category entity, List<Category> subcategories){
        entity.setSubcategories(null);
        this.categoryRepository.saveAndFlush(entity);

        if(subcategories == null){
            return;
        }

        for (Category subcategory : subcategories) {
            saveParnet(subcategory, subcategory.getSubcategories());
        }
    }
}
