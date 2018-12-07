package app.entity.dto.importxml;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class EmployeeXmlDto {

    @NotNull
    @XmlAttribute(name = "first-name")
    private String firstName;

    @NotNull
    @XmlAttribute(name = "last-name")
    private String lastName;

    @NotNull
    @XmlAttribute(name = "position")
    private String position;

    @NotNull
    @XmlElement(name = "card")
    private String card;

    @NotNull
    @XmlElement(name = "branch")
    private String barnch;

    public EmployeeXmlDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getBarnch() {
        return barnch;
    }

    public void setBarnch(String barnch) {
        this.barnch = barnch;
    }
}
