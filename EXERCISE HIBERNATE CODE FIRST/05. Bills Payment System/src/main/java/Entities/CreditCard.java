package Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class CreditCard extends BillingDetail {

    private String cardType;
    private String expirationMonth;
    private String expirationYear;


    public CreditCard(){

    }

    public CreditCard(User user, int number,String cardType, String expirationMonth, String expirationYear){
        super(user, number);
        this.cardType = cardType;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
    }

    @Column(name = "card_type")
    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @Column(name = "exp_month")
    public String getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(String expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    @Column(name = "exp_year")
    public String getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(String expirationYear) {
        this.expirationYear = expirationYear;
    }
}
