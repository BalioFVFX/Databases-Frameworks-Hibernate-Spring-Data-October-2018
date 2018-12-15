package alararestaurant.domain.dtos.importxml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class ItemsListXmlDto {
    @XmlElement(name = "item")
    private List<ItemXmlDto> items;

    public ItemsListXmlDto() {
        this.items = new ArrayList<>();
    }

    public List<ItemXmlDto> getItems() {
        return items;
    }

    public void setItems(List<ItemXmlDto> items) {
        this.items = items;
    }
}
