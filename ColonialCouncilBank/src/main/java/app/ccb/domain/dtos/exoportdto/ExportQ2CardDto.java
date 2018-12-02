package app.ccb.domain.dtos.exoportdto;

public class ExportQ2CardDto {
    private String cardNumber;
    private String cardStatus;

    public ExportQ2CardDto() {
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

}
