package app.dto;

import java.math.BigDecimal;

public class Query4SoldProductsDto {
    private String name;
    private BigDecimal price;

    public Query4SoldProductsDto() {

    }

    public Query4SoldProductsDto(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

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

}
