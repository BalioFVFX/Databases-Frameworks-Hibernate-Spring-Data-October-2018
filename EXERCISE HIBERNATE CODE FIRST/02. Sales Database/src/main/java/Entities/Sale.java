package Entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sales")
public class Sale {
    private int id;
    private Product product;
    private Customer customer;
    private StoreLocation sa;
    private Date date;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "store_location_id")
    public StoreLocation getSa() {
        return sa;
    }

    public void setSa(StoreLocation sa) {
        this.sa = sa;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
