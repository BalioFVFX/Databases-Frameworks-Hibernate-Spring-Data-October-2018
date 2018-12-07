package app.entity.dto.exportxml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class TownXmlDto {
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "population")
    private Integer population;
    @XmlAttribute(name = "town_clients")
    private Long townClients;

    public TownXmlDto() {
    }

    public TownXmlDto(String name, Integer population, Long townClients) {
        this.name = name;
        this.population = population;
        this.townClients = townClients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Long getTownClients() {
        return townClients;
    }

    public void setTownClients(Long townClients) {
        this.townClients = townClients;
    }
}
