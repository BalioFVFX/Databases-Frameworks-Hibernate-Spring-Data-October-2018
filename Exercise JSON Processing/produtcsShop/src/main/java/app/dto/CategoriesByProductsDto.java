package app.dto;

import java.math.BigDecimal;

public class CategoriesByProductsDto {
    private String category;
    private BigDecimal productsCount;
    private BigDecimal averagePrice;
    private BigDecimal totalRevenue;

    public CategoriesByProductsDto(String category, BigDecimal productsCount, BigDecimal averagePrice, BigDecimal totalRevenue) {
        this.category = category;
        this.productsCount = productsCount;
        this.averagePrice = averagePrice;
        this.totalRevenue = totalRevenue;
    }

    public String getCategory() {
        return category;
    }

    public BigDecimal getProductsCount() {
        return productsCount;
    }

    public BigDecimal getAveragePrice() {
        return averagePrice;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }
}
