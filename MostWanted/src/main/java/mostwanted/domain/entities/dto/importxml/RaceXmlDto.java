package mostwanted.domain.entities.dto.importxml;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class RaceXmlDto {
    @XmlElement(name = "laps")
    @NotNull
    private Integer laps;
    @XmlElement(name = "district-name")
    private String districtName;
    @XmlElement(name = "entries")
    private RaceEntrySimpleXmlListDto entries;

    public RaceXmlDto() {
    }

    public Integer getLaps() {
        return laps;
    }

    public void setLaps(Integer laps) {
        this.laps = laps;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public RaceEntrySimpleXmlListDto getEntries() {
        return entries;
    }

    public void setEntries(RaceEntrySimpleXmlListDto entries) {
        this.entries = entries;
    }
}
