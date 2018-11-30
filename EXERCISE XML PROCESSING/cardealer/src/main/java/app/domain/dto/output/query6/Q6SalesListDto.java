package app.domain.dto.output.query6;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "sales")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Q6SalesListDto {
    @XmlElement(name = "sale")
    private List<Q6SaleDto> sales;

    public Q6SalesListDto() {
        this.sales = new ArrayList<>();
    }

    public List<Q6SaleDto> getSales() {
        return sales;
    }

    public void setSales(List<Q6SaleDto> sales) {
        this.sales = sales;
    }
}
