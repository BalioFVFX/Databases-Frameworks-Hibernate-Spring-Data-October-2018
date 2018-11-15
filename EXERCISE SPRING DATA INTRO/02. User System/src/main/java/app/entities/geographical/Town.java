package app.entities.geographical;

import app.entities.BaseEntity;
import app.entities.User;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Town extends BaseEntity {
    private String name;
    private Set<User> currentTownLivingUsers;
    private Set<User> bornTownUsers;
    private Country country;

    public Town() {
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "currentlyLivingTown")
    public Set<User> getCurrentTownLivingUsers() {
        return currentTownLivingUsers;
    }

    public void setCurrentTownLivingUsers(Set<User> currentTownLivingUsers) {
        this.currentTownLivingUsers = currentTownLivingUsers;
    }

    @OneToMany(mappedBy = "bornTown")
    public Set<User> getBornTownUsers() {
        return bornTownUsers;
    }

    public void setBornTownUsers(Set<User> bornTownUsers) {
        this.bornTownUsers = bornTownUsers;
    }

    @ManyToOne(targetEntity = Country.class)
    @JoinColumn(name = "country_id")
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
