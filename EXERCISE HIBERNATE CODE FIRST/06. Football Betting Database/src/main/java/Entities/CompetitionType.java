package Entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "competition_types")
public class CompetitionType {
    private int id;
    private String name;
    private List<Competition> competitions;

    public CompetitionType(){

    }

    public CompetitionType(String name) {
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

    @OneToMany(mappedBy = "competitionType", targetEntity = Competition.class)
    public List<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<Competition> competitions) {
        this.competitions = competitions;
    }
}
