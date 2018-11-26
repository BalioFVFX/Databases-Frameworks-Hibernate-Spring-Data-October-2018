package app.domain.dto.exportDto;

import java.math.BigDecimal;

public class Query4PartsDto {
    private String name;
    private BigDecimal price;

    public Query4PartsDto() {
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
