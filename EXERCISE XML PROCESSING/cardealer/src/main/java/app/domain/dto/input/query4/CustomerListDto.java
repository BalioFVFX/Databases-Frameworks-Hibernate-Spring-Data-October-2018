package app.domain.dto.input.query4;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class CustomerListDto {
    @XmlElement(name = "customer")
    private List<CustomerDto> customers;

    public CustomerListDto() {
        this.customers = new ArrayList<>();
    }

    public List<CustomerDto> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerDto> customers) {
        this.customers = customers;
    }
}
