package Entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "countries")
public class Country {
    private int id;
    private char[] letters;
    private String name;
    private List<Continent> continents;
    private List<Town> towns;


    public Country(){

    }

    public Country(String name){
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

    @Column(length = 3)
    public char[] getLetters() {
        return letters;
    }

    public void setLetters(char[] letters) {
        this.letters = letters;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "countries_continents",
            joinColumns = {@JoinColumn(name = "country_id")},
            inverseJoinColumns = {@JoinColumn(name = "continent_id")})
    public List<Continent> getContinents() {
        return continents;
    }

    public void setContinents(List<Continent> continents) {
        this.continents = continents;
    }

    @OneToMany(mappedBy = "country", targetEntity = Town.class)
    public List<Town> getTowns() {
        return towns;
    }

    public void setTowns(List<Town> towns) {
        this.towns = towns;
    }
}
