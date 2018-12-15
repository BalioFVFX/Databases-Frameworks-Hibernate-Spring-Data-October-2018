package alararestaurant.domain.dtos.importxml;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class ItemXmlDto {
    @Length(min = 3, max = 30)
    @NotNull
    @XmlElement(name = "name")
    private String name;
    @Range(min = 1)
    @NotNull
    @XmlElement(name = "quantity")
    private String quantity;

    public ItemXmlDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
