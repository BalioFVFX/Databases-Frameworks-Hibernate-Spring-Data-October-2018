package app.domain.dto.output.query4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Q4CarListDto {
    @XmlElement(name = "car")
    private List<Q4CarDto> cars;

    public Q4CarListDto() {
        this.cars = new ArrayList<>();
    }

    public List<Q4CarDto> getCars() {
        return cars;
    }

    public void setCars(List<Q4CarDto> cars) {
        this.cars = cars;
    }
}
