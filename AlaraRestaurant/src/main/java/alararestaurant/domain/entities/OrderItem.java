package alararestaurant.domain.entities;

import org.hibernate.validator.constraints.Range;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name = "order_items")
public class OrderItem extends BaseEntity{
    private Order order;
    private Item item;
    private Integer quantity;

    public OrderItem() {
    }

    @ManyToOne(targetEntity = Order.class, optional = false)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @ManyToOne(targetEntity = Item.class, optional = false)
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Range(min = 1)
    @Column(name = "quantity", nullable = false)
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
