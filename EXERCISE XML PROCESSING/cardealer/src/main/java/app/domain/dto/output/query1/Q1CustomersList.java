package app.domain.dto.output.query1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Q1CustomersList {
    @XmlElement(name = "customer")
    private List<Q1CustomerDto> customers;

    public Q1CustomersList() {
        this.customers = new ArrayList<>();
    }

    public List<Q1CustomerDto> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Q1CustomerDto> customers) {
        this.customers = customers;
    }
}
