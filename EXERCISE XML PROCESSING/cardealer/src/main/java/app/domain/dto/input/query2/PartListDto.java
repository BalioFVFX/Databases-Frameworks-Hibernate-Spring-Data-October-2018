package app.domain.dto.input.query2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "parts")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class PartListDto {
    @XmlElement(name = "part")
    private List<PartDto> parts;

    public PartListDto() {
        this.parts = new ArrayList<>();
    }

    public List<PartDto> getParts() {
        return parts;
    }

    public void setParts(List<PartDto> parts) {
        this.parts = parts;
    }
}
