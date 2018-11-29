package app.domain.dtos.output.query4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class UsersAndProductsProductsListDto {
    @XmlElement(name = "product")
    private List<UsersAndProductsProductDto> products;

    public UsersAndProductsProductsListDto() {
        this.products = new ArrayList<>();
    }

    public UsersAndProductsProductsListDto(List<UsersAndProductsProductDto> products) {
        this.products = products;
    }

    public List<UsersAndProductsProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<UsersAndProductsProductDto> products) {
        this.products = products;
    }
}
