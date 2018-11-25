package app.service;

import app.dto.CategoriesByProductsDto;
import app.repository.CategoryRepository;
import app.util.mapper.CMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {

    final private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoriesByProductsDto> query3() {
        List<CategoriesByProductsDto> result = new ArrayList<>();

        List<Object[]> objects = this.categoryRepository.query3();

        for (Object[] objArr: objects){
            CategoriesByProductsDto categoriesByProductsDto =
                    new CategoriesByProductsDto(objArr[0].toString(), new BigDecimal(objArr[1].toString()),
                            new BigDecimal(objArr[2].toString()), new BigDecimal(objArr[3].toString()));
            result.add(categoriesByProductsDto);
        }
        return result;
    }
}
