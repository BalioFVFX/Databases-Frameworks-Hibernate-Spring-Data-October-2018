package app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "employee_cards")
public class EmployeeCard extends BaseEntity{
    private String number;

    public EmployeeCard() {
    }

    @Column(name = "number", nullable = false)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
