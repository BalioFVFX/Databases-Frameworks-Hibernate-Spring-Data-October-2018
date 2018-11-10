package Entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "towns")
public class Town {
    private int id;
    private String name;
    private Country country;
    private List<Team> teams;

    public Town() {

    }

    public Town(String name) {
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

    @ManyToOne(targetEntity = Country.class)
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @OneToMany(mappedBy = "town", targetEntity = Team.class)
    public List<Team> getTeam() {
        return teams;
    }

    public void setTeam(List<Team> teams) {
        this.teams = teams;
    }
}
