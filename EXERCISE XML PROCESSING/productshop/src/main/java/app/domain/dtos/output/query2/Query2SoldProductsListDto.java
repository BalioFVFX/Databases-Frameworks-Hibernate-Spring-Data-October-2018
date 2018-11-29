package app.domain.dtos.output.query2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class Query2SoldProductsListDto {
    @XmlElement(name = "product")
    private List<Query2SoldProductsDto> products;

    public Query2SoldProductsListDto() {
        this.products = new ArrayList<>();
    }

    public List<Query2SoldProductsDto> getProducts() {
        return products;
    }

    public void setProducts(List<Query2SoldProductsDto> products) {
        this.products = products;
    }
}
