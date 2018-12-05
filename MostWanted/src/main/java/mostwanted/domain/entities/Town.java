package mostwanted.domain.entities;

import mostwanted.domain.entities.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "towns")
public class Town extends BaseEntity {

    private String name;
    private List<District> districts;
    private List<Racer> racers;

    public Town() {
    }

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(targetEntity = District.class, mappedBy = "town")
    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

    @OneToMany(targetEntity = Racer.class, mappedBy = "homeTown")
    public List<Racer> getRacers() {
        return racers;
    }

    public void setRacers(List<Racer> racers) {
        this.racers = racers;
    }
}