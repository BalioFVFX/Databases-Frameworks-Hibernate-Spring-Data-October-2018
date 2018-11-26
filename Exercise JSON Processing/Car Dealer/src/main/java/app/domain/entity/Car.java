package app.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car extends BaseEntity{
    private String make;
    private String model;
    private Long travelledDistance;
    private List<Part> parts;
    private List<Sale> sales;

    public Car() {
    }

    @Column
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @Column
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "travelled_distance")
    public Long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }


    @ManyToMany(targetEntity = Part.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "parts_cars",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "part_id"))
    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    @OneToMany(targetEntity = Sale.class, mappedBy = "car")
    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }
}
