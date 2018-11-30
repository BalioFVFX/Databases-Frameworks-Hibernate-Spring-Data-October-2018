package app.domain.dto.output.query6;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class Q6SaleDto {
    @XmlElement(name = "car")
    private Q6CarDto q6CarDto;
    @XmlElement(name = "customer-name")
    private String customerName;
    @XmlElement
    private Double discount;
    @XmlElement
    private BigDecimal price;
    @XmlElement(name = "price-with-discount")
    private BigDecimal priceWithDiscount;


    public Q6CarDto getQ6CarDto() {
        return q6CarDto;
    }

    public void setQ6CarDto(Q6CarDto q6CarDto) {
        this.q6CarDto = q6CarDto;
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
