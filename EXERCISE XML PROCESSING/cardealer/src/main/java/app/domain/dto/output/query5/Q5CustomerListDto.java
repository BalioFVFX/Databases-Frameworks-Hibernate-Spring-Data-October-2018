package app.domain.dto.output.query5;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Q5CustomerListDto {
    @XmlElement(name = "customer")
    private List<Q5CustomerDto> customers;

    public Q5CustomerListDto() {
        this.customers = new ArrayList<>();
    }

    public List<Q5CustomerDto> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Q5CustomerDto> customers) {
        this.customers = customers;
    }
}
