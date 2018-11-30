package app.domain.dto.output.query4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class Q4PartListDto {
    @XmlElement(name = "part")
    private List<Q4PartDto> parts;

    public Q4PartListDto() {
        this.parts = new ArrayList<>();
    }

    public List<Q4PartDto> getParts() {
        return parts;
    }

    public void setParts(List<Q4PartDto> parts) {
        this.parts = parts;
    }
}
