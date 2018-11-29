package app.domain.dtos.output.query1;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ProductInRangeListDto {
    @XmlElement(name = "product")
    private List<ProductsInRangeDto> products;

    public ProductInRangeListDto() {
    }

    public List<ProductsInRangeDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsInRangeDto> products) {
        this.products = products;
    }
}
