package app.service;

import app.dto.CategoryDto;
import app.dto.ProductCreateDto;
import app.dto.ProductsInRangeDto;
import app.dto.SuccessfullySoldProductDetailDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void seedJson(List<ProductCreateDto> products, List<CategoryDto> categories);
    List<ProductsInRangeDto> productsInRange(BigDecimal lowerPrice, BigDecimal greaterPrice);

}
