package Entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "store_locations")
public class StoreLocation {
    private int id;
    private String locationName;
    private Set<Sale> sales;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "location_name")
    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @OneToMany(mappedBy = "sa")
    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}
