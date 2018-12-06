package app.domain.dto.importxml.userpart;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "participations")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class UserPartList {
    @XmlElement(name = "participation")
    private List<UserPart> participations;

    public UserPartList() {
    }

    public List<UserPart> getParticipations() {
        return participations;
    }

    public void setParticipations(List<UserPart> participations) {
        this.participations = participations;
    }
}
