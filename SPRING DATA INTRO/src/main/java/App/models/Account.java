package App.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
public class Account {
    private long id;
    private BigDecimal balance;
    private User user;

    public Account(){

    }

    public Account(BigDecimal balance, User user){
        setBalance(balance);
        setUser(user);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        if(balance.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Negative balance is not allowed");
        }
        this.balance = balance;
    }

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "u_id", insertable = true, referencedColumnName = "id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
