package Entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "continents")
public class Continent {
    private int id;
    private String name;
    private List<Country> countries;


    public Continent(){

    }

    public Continent(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "continents")
    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}
