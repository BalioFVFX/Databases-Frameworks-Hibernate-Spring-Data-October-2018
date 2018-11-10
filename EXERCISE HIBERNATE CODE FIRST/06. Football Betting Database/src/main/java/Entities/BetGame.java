package Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bet_games")
public class BetGame implements Serializable {
    private Game game;
    private Bet bet;
    private ResultPrediction resultPrediction;
    public BetGame(){

    }

    @Id
    @ManyToOne
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Id
    @ManyToOne
    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }

    @OneToOne
    public ResultPrediction getResultPrediction() {
        return resultPrediction;
    }

    public void setResultPrediction(ResultPrediction resultPrediction) {
        this.resultPrediction = resultPrediction;
    }
}
