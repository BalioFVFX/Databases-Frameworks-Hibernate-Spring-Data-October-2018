package app.domain.entities;

import org.hibernate.validator.constraints.Length;


import javax.persistence.*;
import java.util.List;

@Entity(name = "categories")
public class Category extends BaseEntity{
    private String name;
    List<Product> products;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    @Length(min = 3, max = 15)
    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(targetEntity = Product.class)
    @JoinTable(name = "category_products", joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
