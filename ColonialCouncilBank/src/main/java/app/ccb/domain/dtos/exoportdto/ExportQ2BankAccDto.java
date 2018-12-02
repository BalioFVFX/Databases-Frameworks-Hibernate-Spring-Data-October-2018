package app.ccb.domain.dtos.exoportdto;

import java.util.List;

public class ExportQ2BankAccDto {
    private List<ExportQ2CardDto> cards;
    private String accountNumber;

    public ExportQ2BankAccDto() {
    }

    public List<ExportQ2CardDto> getCards() {
        return cards;
    }

    public void setCards(List<ExportQ2CardDto> cards) {
        this.cards = cards;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
