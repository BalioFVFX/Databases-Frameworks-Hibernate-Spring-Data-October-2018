package app.service.category;

import app.domain.dtos.output.query3.CategoriesByProductCountDto;
import app.domain.dtos.output.query3.CategoriesByProductsCountListDto;
import app.domain.dtos.seeders.CategorySeederDto;
import app.domain.entities.Category;
import app.domain.entities.Product;
import app.modelmapperconfigs.CMapper;
import app.repository.CategoryRepository;
import app.repository.ProductRepository;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void saveAll(List<CategorySeederDto> categories) {
        List<Product> productEntities = this.productRepository.findAll();
        Type listType = new TypeToken<List<Category>>() {}.getType();
        List<Category> categoryEntities = CMapper.mapper().map(categories, listType);

        Random rnd = new Random();
        for (Category categoryEntity: categoryEntities){
            Integer randomProductIndex1 = rnd.nextInt(productEntities.size() - 1);
            Integer randomProductIndex2 = rnd.nextInt(productEntities.size() - 1);

            while(randomProductIndex1.equals(randomProductIndex2)){
                randomProductIndex1 = rnd.nextInt(productEntities.size() - 1);
                randomProductIndex2 = rnd.nextInt(productEntities.size() - 1);
            }

            List<Product> randomProducts = new ArrayList<>();
            randomProducts.add(productEntities.get(randomProductIndex1));
            randomProducts.add(productEntities.get(randomProductIndex2));
            categoryEntity.setProducts(randomProducts);
        }

        this.categoryRepository.saveAll(categoryEntities);
    }

    @Override
    public CategoriesByProductsCountListDto categoriesByProductsCount() {
        List<Object[]> objectListEntities = this.categoryRepository.categoriesByProductCountQuery3();
        CategoriesByProductsCountListDto result = new CategoriesByProductsCountListDto();
        for (Object[] objectArr: objectListEntities){
            CategoriesByProductCountDto categoriesByProductCountDto =
                    new CategoriesByProductCountDto(objectArr[0].toString(), Integer.parseInt(objectArr[1].toString()),
                            new BigDecimal(objectArr[2].toString()), new BigDecimal(objectArr[3].toString()));
            result.getCategories().add(categoriesByProductCountDto);
        }
        return result;
    }
}
