package app.domain.dto.importDto;

import app.domain.entity.Part;

import java.math.BigDecimal;
import java.util.List;

public class PartImportDto {
    private String name;
    private BigDecimal price;
    private Integer quantity;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
