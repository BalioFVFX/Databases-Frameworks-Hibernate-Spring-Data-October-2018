package app.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "parts")
public class Part extends BaseEntity{
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private List<Car> cars;
    private PartSupplier partSupplier;

    public Part() {
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @ManyToMany(targetEntity = Car.class, mappedBy = "parts")
    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @ManyToOne(targetEntity = PartSupplier.class)
    public PartSupplier getPartSupplier() {
        return partSupplier;
    }

    public void setPartSupplier(PartSupplier partSupplier) {
        this.partSupplier = partSupplier;
    }
}
