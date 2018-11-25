package app.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
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

    @ManyToMany(targetEntity = Product.class, mappedBy = "categories")
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
