package app.domain.dto.exportDto;

import java.math.BigDecimal;

public class Query6Dto {
    private Query4CarDto car;

    private String customerName;
    private Double discount;
    private BigDecimal price;
    private BigDecimal priceWithDiscount;


    public Query4CarDto getCar() {
        return car;
    }

    public void setCar(Query4CarDto car) {
        this.car = car;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }
}
