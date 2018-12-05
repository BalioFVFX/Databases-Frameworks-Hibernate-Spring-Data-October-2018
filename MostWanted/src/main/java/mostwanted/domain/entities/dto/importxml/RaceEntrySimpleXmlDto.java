package mostwanted.domain.entities.dto.importxml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


@XmlAccessorType(value = XmlAccessType.FIELD)
public class RaceEntrySimpleXmlDto {
    @XmlAttribute(name = "id")
    private Integer id;

    public RaceEntrySimpleXmlDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
