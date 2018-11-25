package app.dto;

import java.math.BigDecimal;

public class ProductCreateDto {
    private String name;
    private BigDecimal price;
    private Long sellerId;
    private Long buyerId;

    public ProductCreateDto(String name, BigDecimal price, Long sellerId, Long buyerId) {
        this.name = name;
        this.price = price;
        this.sellerId = sellerId;
        this.buyerId = buyerId;
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

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }
}
