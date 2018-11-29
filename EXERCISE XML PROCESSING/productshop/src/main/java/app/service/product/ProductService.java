package app.service.product;

import app.domain.dtos.output.query1.ProductInRangeListDto;
import app.domain.dtos.seeders.ProductSeederDto;

import java.util.List;

public interface ProductService {
    void saveAll(List<ProductSeederDto> products);
    ProductInRangeListDto productsInRange();
}
