package app.ccb.domain.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "clients")
public class Client extends BaseEntity{
    private String fullName;
    private Integer age;
    private BankAccount bankAccount;
    private List<Employee> appointedEmployees;

    public Client() {
        this.appointedEmployees = new ArrayList<>();
    }

    @Column(name = "full_name", nullable = false)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @OneToOne(targetEntity = BankAccount.class, mappedBy = "client")
    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @ManyToMany(targetEntity = Employee.class)
    @JoinTable(name = "employees_clients",
    joinColumns = @JoinColumn(name = "client_id"),
    inverseJoinColumns = @JoinColumn(name = "employee_id"))
    public List<Employee> getAppointedEmployees() {
        return appointedEmployees;
    }

    public void setAppointedEmployees(List<Employee> appointedEmployees) {
        this.appointedEmployees = appointedEmployees;
    }
}
