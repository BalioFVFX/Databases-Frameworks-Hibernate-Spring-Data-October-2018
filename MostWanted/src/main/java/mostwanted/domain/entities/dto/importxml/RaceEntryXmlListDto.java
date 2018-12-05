package mostwanted.domain.entities.dto.importxml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "race-entries")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class RaceEntryXmlListDto {
    @XmlElement(name = "race-entry")
    private List<RaceEntryXmlDto> raceEntries;

    public RaceEntryXmlListDto() {
        this.raceEntries = new ArrayList<>();
    }

    public List<RaceEntryXmlDto> getRaceEntries() {
        return raceEntries;
    }

    public void setRaceEntries(List<RaceEntryXmlDto> raceEntries) {
        this.raceEntries = raceEntries;
    }
}
