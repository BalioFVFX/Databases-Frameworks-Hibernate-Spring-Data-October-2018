package app.entity.dto.importxml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "employees")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class EmployeeXmlListDto {
    @XmlElement(name = "employee")
    private List<EmployeeXmlDto> employees;

    public EmployeeXmlListDto() {
        this.employees = new ArrayList<>();
    }

    public List<EmployeeXmlDto> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeXmlDto> employees) {
        this.employees = employees;
    }
}
