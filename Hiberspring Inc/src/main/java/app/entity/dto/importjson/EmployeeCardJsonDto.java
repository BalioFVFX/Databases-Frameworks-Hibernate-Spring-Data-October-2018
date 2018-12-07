package app.entity.dto.importjson;

import javax.validation.constraints.NotNull;

public class EmployeeCardJsonDto {
    private String number;

    public EmployeeCardJsonDto() {
    }

    @NotNull
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
