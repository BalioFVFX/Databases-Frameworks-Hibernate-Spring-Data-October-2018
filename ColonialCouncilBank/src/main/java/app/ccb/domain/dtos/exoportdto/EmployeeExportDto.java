package app.ccb.domain.dtos.exoportdto;

import java.math.BigDecimal;
import java.util.List;

public class EmployeeExportDto {
    private String fullName;
    private BigDecimal salary;
    private String startedOn;
    private List<ClientExportDto> clients;

    public EmployeeExportDto() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getStartedOn() {
        return startedOn;
    }

    public void setStartedOn(String startedOn) {
        this.startedOn = startedOn;
    }

    public List<ClientExportDto> getClients() {
        return clients;
    }

    public void setClients(List<ClientExportDto> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Full Name: %s", this.fullName)).append(System.lineSeparator());
        stringBuilder.append(String.format("Salary: %s", this.salary)).append(System.lineSeparator());
        stringBuilder.append(String.format("Started On: %s", this.startedOn)).append(System.lineSeparator());
        stringBuilder.append("Clients:").append(System.lineSeparator());
        for (ClientExportDto client: clients){
            stringBuilder.append("  ").append(client.getFullName()).append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }
}
