package Entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bets")
public class Bet {
    private int id;
    private Double moeny;
    private Date betDate;
    private User user;

    public Bet(){

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
    public Double getMoeny() {
        return moeny;
    }

    public void setMoeny(Double moeny) {
        this.moeny = moeny;
    }

    @Column
    public Date getBetDate() {
        return betDate;
    }

    public void setBetDate(Date betDate) {
        this.betDate = betDate;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
