package Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "players")
public class Player {
    private int id;
    private String name;
    private int squadNumber;
    private Team team;
    private TeamPosition teamPosition;
    private boolean isCurrentlyInjured;
    private List<Game> games;
    private List<PlayerStatistic> statistics;

    public Player(){

    }

    public Player(String name){
     this.name = name;
     this.games = new ArrayList<Game>();
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

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "squad_number")
    public int getSquadNumber() {
        return squadNumber;
    }

    public void setSquadNumber(int squadNumber) {
        this.squadNumber = squadNumber;
    }

    @ManyToOne(targetEntity = Team.class, cascade = CascadeType.ALL)
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @ManyToOne(targetEntity = TeamPosition.class)
    public TeamPosition getTeamPosition() {
        return teamPosition;
    }

    public void setTeamPosition(TeamPosition teamPosition) {
        this.teamPosition = teamPosition;
    }

    @Column(name = "currenty_injured")
    public boolean isCurrentlyInjured() {
        return isCurrentlyInjured;
    }

    public void setCurrentlyInjured(boolean currentlyInjured) {
        isCurrentlyInjured = currentlyInjured;
    }


    @OneToMany()
    @JoinColumn(name = "id", referencedColumnName = "id", insertable=false, updatable=false)
    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }


    @OneToMany(mappedBy = "player", targetEntity = PlayerStatistic.class)
    public List<PlayerStatistic> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<PlayerStatistic> statistics) {
        this.statistics = statistics;
    }
}
