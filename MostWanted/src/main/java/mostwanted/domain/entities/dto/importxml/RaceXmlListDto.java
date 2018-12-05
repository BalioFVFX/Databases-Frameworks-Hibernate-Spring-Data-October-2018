package mostwanted.domain.entities.dto.importxml;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "races")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class RaceXmlListDto {
    @XmlElement(name = "race")
    private List<RaceXmlDto> races;

    public RaceXmlListDto() {
        this.races = new ArrayList<>();
    }

    public List<RaceXmlDto> getRaces() {
        return races;
    }

    public void setRaces(List<RaceXmlDto> races) {
        this.races = races;
    }
}
