package Entities;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "result_predictions")
public class ResultPrediction {
    private int id;
    private Predictions predictions;
    private List<Bet> bets;

    public ResultPrediction(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Enumerated(EnumType.STRING)
    public Predictions getPredictions() {
        return predictions;
    }

    public void setPredictions(Predictions predictions) {
        this.predictions = predictions;
    }

    @OneToMany
    @JoinColumn(name = "id")
    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }
}
