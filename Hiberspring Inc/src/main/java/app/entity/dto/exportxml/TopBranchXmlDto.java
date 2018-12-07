package app.entity.dto.exportxml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class TopBranchXmlDto {
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "town")
    private String town;
    @XmlAttribute(name = "total_clients")
    private Long totalClients;

    public TopBranchXmlDto() {
    }

    public TopBranchXmlDto(String name, String town, Long totalClients) {
        this.name = name;
        this.town = town;
        this.totalClients = totalClients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public Long getTotalClients() {
        return totalClients;
    }

    public void setTotalClients(Long totalClients) {
        this.totalClients = totalClients;
    }
}
