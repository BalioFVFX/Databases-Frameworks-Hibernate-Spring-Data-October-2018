package alararestaurant.domain.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "orders")
public class Order extends BaseEntity{
    private String customer;
    private LocalDateTime dateTime;
    private String type;
    private Employee employee;
    private List<OrderItem> orderItems;

    public Order() {
    }


    @Column(name = "customer", columnDefinition = "TEXT NOT NULL")
    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @Column(name = "date_time", nullable = false)
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }


    @Column(name="status", columnDefinition="enum('ForHere','ToGo') default 'ForHere' NOT NULL")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @ManyToOne(targetEntity = Employee.class, optional = false)
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @OneToMany(targetEntity = OrderItem.class, mappedBy = "order", cascade = CascadeType.ALL)
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
