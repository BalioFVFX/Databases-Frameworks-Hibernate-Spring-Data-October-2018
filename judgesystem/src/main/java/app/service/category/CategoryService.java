package app.service.category;

import app.domain.dto.importjson.category.CategoryJsonDto;

import java.util.List;

public interface CategoryService {
    void saveJson(List<CategoryJsonDto> categoryJsonDtoList);
}
