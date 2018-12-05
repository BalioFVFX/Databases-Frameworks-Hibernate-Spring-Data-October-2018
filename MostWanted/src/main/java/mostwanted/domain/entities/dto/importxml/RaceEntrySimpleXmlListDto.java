package mostwanted.domain.entities.dto.importxml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class RaceEntrySimpleXmlListDto {
    @XmlElement(name = "entry")
    private List<RaceEntrySimpleXmlDto> entries;

    public RaceEntrySimpleXmlListDto() {
        this.entries = new ArrayList<>();
    }

    public List<RaceEntrySimpleXmlDto> getEntries() {
        return entries;
    }

    public void setEntries(List<RaceEntrySimpleXmlDto> entries) {
        this.entries = entries;
    }
}
