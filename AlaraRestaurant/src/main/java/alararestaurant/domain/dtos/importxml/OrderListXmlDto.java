package alararestaurant.domain.dtos.importxml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "orders")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class OrderListXmlDto {
    @XmlElement(name = "order")
    private List<OrderXmlDto> orders;

    public OrderListXmlDto() {
        this.orders = new ArrayList<>();
    }

    public List<OrderXmlDto> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderXmlDto> orders) {
        this.orders = orders;
    }
}
