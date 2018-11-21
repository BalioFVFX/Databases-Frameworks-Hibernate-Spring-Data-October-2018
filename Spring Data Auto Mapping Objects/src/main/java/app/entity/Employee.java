package app.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {
    private Long id;
    private String firstName;
    private String lastName;

    private BigDecimal salary;
    private LocalDate birthday;
    private String address;
    private Boolean onHoliday;
    private Employee manager;
    private List<Employee> chargeEmployees;

    public Employee() {
    }

    public Employee(String firstName, String lastName, BigDecimal salary, LocalDate birthday, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.birthday = birthday;
        this.address = address;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "salary", nullable = false)
    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Column(name = "birthday", nullable = false)
    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Column(name = "address", nullable = false)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Column(name = "on_holiday")
    public Boolean getOnHoliday() {
        return onHoliday;
    }

    public void setOnHoliday(Boolean onHoliday) {
        this.onHoliday = onHoliday;
    }

    @ManyToOne(targetEntity = Employee.class)
    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }
    @OneToMany(mappedBy = "manager", targetEntity = Employee.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Employee> getChargeEmployees() {
        return chargeEmployees;
    }

    public void setChargeEmployees(List<Employee> chargeEmployees) {
        this.chargeEmployees = chargeEmployees;
    }
}
