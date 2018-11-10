package Entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "useres")
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private Double balance;
    private List<Bet> bets;

    public User(){

    }

    public User(String username, String fullName) {
        this.username = username;
        this.fullName = fullName;
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
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column
    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }


    @OneToMany(mappedBy = "user")
    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }
}
