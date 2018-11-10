package Entities;



import javax.persistence.*;

@Entity
@Table(name = "colors")
public class TeamColor {
    private int id;
    private String name;
    private Team team;

    public TeamColor(){

    }

    public TeamColor(String name){
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

    @OneToOne(targetEntity = Team.class)
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
