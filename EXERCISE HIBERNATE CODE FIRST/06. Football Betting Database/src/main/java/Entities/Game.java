package Entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "games")
public class Game {
    private int id;
    private Team homeTeam;
    private Team awayTeam;
    private int homeGoals;
    private int awayGoals;
    private Date gameDate;
    private Double homeTeamWinRate;
    private Double awayTeamWinRate;
    private Double drawGameRate;
    private Round round;
    private Competition competition;
    private List<Player> players;
    private List<BetGame> betGames;

    public Game(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    @ManyToOne
    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(int homeGoals) {
        this.homeGoals = homeGoals;
    }

    public int getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(int awayGoals) {
        this.awayGoals = awayGoals;
    }

    public Date getGameDate() {
        return gameDate;
    }

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }

    public Double getHomeTeamWinRate() {
        return homeTeamWinRate;
    }

    public void setHomeTeamWinRate(Double homeTeamWinRate) {
        this.homeTeamWinRate = homeTeamWinRate;
    }

    public Double getAwayTeamWinRate() {
        return awayTeamWinRate;
    }

    public void setAwayTeamWinRate(Double awayTeamWinRate) {
        this.awayTeamWinRate = awayTeamWinRate;
    }

    public Double getDrawGameRate() {
        return drawGameRate;
    }

    public void setDrawGameRate(Double drawGameRate) {
        this.drawGameRate = drawGameRate;
    }

    @ManyToOne
    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    @ManyToOne
    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }


    @OneToMany
    @JoinColumn(name = "id", referencedColumnName = "id", insertable=false, updatable=false)
    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @OneToMany(mappedBy = "game")
    public List<BetGame> getBetGames() {
        return betGames;
    }

    public void setBetGames(List<BetGame> betGames) {
        this.betGames = betGames;
    }
}
