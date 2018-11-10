package Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "player_statistics")
public class PlayerStatistic implements Serializable {
    private Game game;
    private Player player;
    private int scoredGoals;
    private int playerAssists;
    private int playedMinutes;

    public PlayerStatistic(){

    }

    @Id
    @OneToOne
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Id
    @ManyToOne(targetEntity = Player.class)
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getScoredGoals() {
        return scoredGoals;
    }

    public void setScoredGoals(int scoredGoals) {
        this.scoredGoals = scoredGoals;
    }

    public int getPlayerAssists() {
        return playerAssists;
    }

    public void setPlayerAssists(int playerAssists) {
        this.playerAssists = playerAssists;
    }

    public int getPlayedMinutes() {
        return playedMinutes;
    }

    public void setPlayedMinutes(int playedMinutes) {
        this.playedMinutes = playedMinutes;
    }
}
