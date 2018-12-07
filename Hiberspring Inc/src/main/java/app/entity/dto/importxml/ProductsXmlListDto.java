package app.entity.dto.importxml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ProductsXmlListDto {
    @XmlElement(name = "product")
    private List<ProductXmlDto> products;

    public ProductsXmlListDto() {
        this.products = new ArrayList<>();
    }

    public List<ProductXmlDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductXmlDto> products) {
        this.products = products;
    }
}
