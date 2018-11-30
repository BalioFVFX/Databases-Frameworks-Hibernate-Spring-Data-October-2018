package app.domain.dto.output.query2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Q2ToyotaListDto {
    @XmlElement(name = "car")
    private List<Q2ToyotaDto> cars;

    public Q2ToyotaListDto() {
        this.cars = new ArrayList<>();
    }

    public List<Q2ToyotaDto> getCars() {
        return cars;
    }

    public void setCars(List<Q2ToyotaDto> cars) {
        this.cars = cars;
    }
}
