package Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teams")
public class Team {
    private int id;
    private String name;
    private char[] letters;
    private TeamColor primaryColor;
    private TeamColor secondaryColor;
    private Town town;
    private Double budget;
    private List<Player> players;


    public Team(){

    }

    public Team(String name){
        this.name = name;
        this.players = new ArrayList<Player>();
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

    @Column
    public char[] getLetters() {
        return letters;
    }

    public void setLetters(char[] letters) {
        this.letters = letters;
    }

    @OneToOne(mappedBy = "team", targetEntity = TeamColor.class)
    public TeamColor getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(TeamColor primaryColor) {
        this.primaryColor = primaryColor;
    }

    @OneToOne(mappedBy = "team", targetEntity = TeamColor.class)
    public TeamColor getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(TeamColor secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    @ManyToOne(targetEntity = Town.class)
    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    @Column
    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    @OneToMany(mappedBy = "team")
    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
