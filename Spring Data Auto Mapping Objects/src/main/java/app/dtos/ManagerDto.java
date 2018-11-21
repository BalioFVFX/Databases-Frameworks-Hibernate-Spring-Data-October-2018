package app.dtos;

import app.entity.Employee;

import java.util.List;

public class ManagerDto {
    private String firstName;
    private String lastName;
    private List<EmployeeDto> empCharge;
    private Integer empChargeSize;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<EmployeeDto> getEmpCharge() {
        return empCharge;
    }

    public void setEmpCharge(List<EmployeeDto> empCharge) {
        this.empCharge = empCharge;
    }

    public Integer getEmpChargeSize() {
        return empChargeSize;
    }

    public void setEmpChargeSize(List<Employee> empList) {
        this.empChargeSize = empList.size();
    }
}
