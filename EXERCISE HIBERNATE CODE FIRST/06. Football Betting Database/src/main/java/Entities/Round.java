package Entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rounds")
public class Round {
    private int id;
    private String name;
    private List<Game> games;

    public Round(){

    }

    public Round(String name){
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


    @OneToMany(mappedBy = "round")
    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
