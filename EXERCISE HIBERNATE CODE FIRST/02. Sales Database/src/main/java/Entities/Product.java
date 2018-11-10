package Entities;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {

    private int id;
    private String name;
    private Double qunatity;
    private BigDecimal price;
    private Set<Sale> sales;

    public Product(){

    }

    public Product(String name, Double qunatity, BigDecimal price) {
        this.name = name;
        this.qunatity = qunatity;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Column(name = "quantity")
    public Double getQunatity() {
        return qunatity;
    }

    public void setQunatity(Double qunatity) {
        this.qunatity = qunatity;
    }

    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @OneToMany(targetEntity = Sale.class)
    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}
