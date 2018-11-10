package Entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "positions")
public class TeamPosition {
    private int id;
    private char[] letters;
    private String description;
    private List<Player> players;

    public TeamPosition(){

    }

    public TeamPosition(String description){
        this.description = description;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(length = 2)
    public char[] getLetters() {
        return letters;
    }

    public void setLetters(char[] letters) {
        this.letters = letters;
    }

    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(mappedBy = "teamPosition", targetEntity = Player.class)
    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
