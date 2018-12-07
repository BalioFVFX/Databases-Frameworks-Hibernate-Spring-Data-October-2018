package app.entity.dto.exportxml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "towns")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class TownListXmlDto {
    @XmlElement(name = "town")
    private List<TownXmlDto> towns;

    public TownListXmlDto() {
        this.towns = new ArrayList<>();
    }

    public List<TownXmlDto> getTowns() {
        return towns;
    }

    public void setTowns(List<TownXmlDto> towns) {
        this.towns = towns;
    }
}
