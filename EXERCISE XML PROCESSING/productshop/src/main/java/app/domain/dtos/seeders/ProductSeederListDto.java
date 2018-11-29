package app.domain.dtos.seeders;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ProductSeederListDto {
    @XmlElement(name = "product")
    private List<ProductSeederDto> products;

    public List<ProductSeederDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductSeederDto> products) {
        this.products = products;
    }
}
