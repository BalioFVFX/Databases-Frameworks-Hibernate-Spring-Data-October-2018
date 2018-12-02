package app.ccb.domain.dtos.exoportdto;

public class ExportQ2ClientDto {
    private String fullName;
    private Integer age;
    private ExportQ2BankAccDto bankAccount;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public ExportQ2BankAccDto getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(ExportQ2BankAccDto bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Full Name: %s", this.fullName)).append(System.lineSeparator());
        stringBuilder.append(String.format("Age: %d", this.age)).append(System.lineSeparator());
        stringBuilder.append(String.format("Bank Account: %s", this.bankAccount.getAccountNumber())).append(System.lineSeparator());

        for (ExportQ2CardDto cardDto: bankAccount.getCards()){
            stringBuilder.append(String.format("    Card Number: %s", cardDto.getCardNumber())).append(System.lineSeparator());
            stringBuilder.append(String.format("    Card Status: %s", cardDto.getCardStatus())).append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }
}
