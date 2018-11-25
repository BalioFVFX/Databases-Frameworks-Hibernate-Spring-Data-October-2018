package app.dto;

import java.util.List;

public class Query4SoldProductsCountDto {
    private Integer count;
    private List<Query4SoldProductsDto> products;

    public Query4SoldProductsCountDto() {
    }


    public Query4SoldProductsCountDto(Integer count, List<Query4SoldProductsDto> products) {
        this.count = count;
        this.products = products;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Query4SoldProductsDto> getProducts() {
        return products;
    }

    public void setProducts(List<Query4SoldProductsDto> products) {
        this.products = products;
    }
}
