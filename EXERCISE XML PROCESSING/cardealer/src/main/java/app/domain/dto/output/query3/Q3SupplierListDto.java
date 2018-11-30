package app.domain.dto.output.query3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "supplier")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Q3SupplierListDto {
    private List<Q3SupplierDto> suppliers;

    public Q3SupplierListDto() {
        this.suppliers = new ArrayList<>();
    }

    public List<Q3SupplierDto> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Q3SupplierDto> suppliers) {
        this.suppliers = suppliers;
    }
}
