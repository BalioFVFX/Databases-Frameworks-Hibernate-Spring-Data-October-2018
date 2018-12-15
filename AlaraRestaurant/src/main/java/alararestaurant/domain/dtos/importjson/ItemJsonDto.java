package alararestaurant.domain.dtos.importjson;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ItemJsonDto {
    @Expose
    @Length(min = 3, max = 30)
    @NotNull
    private String name;
    @Expose
    @DecimalMin("0.01")
    @NotNull
    private BigDecimal price;
    @Expose
    @Length(min = 3, max = 30)
    @NotNull
    private String category;

    public ItemJsonDto() {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
