package app.domain.dto.importxml.userpart;

import app.domain.dto.importxml.problem.ContestXmlDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class UserPart {
    @XmlElement(name = "contest")
    private ContestXmlDto contest;
    @XmlElement(name = "user")
    private UserXmlDto user;

    public UserPart() {
    }

    public ContestXmlDto getContest() {
        return contest;
    }

    public void setContest(ContestXmlDto contest) {
        this.contest = contest;
    }

    public UserXmlDto getUser() {
        return user;
    }

    public void setUser(UserXmlDto user) {
        this.user = user;
    }
}
