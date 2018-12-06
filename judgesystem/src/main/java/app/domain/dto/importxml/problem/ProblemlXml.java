package app.domain.dto.importxml.problem;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class ProblemlXml {
    @XmlElement(name = "id")
    private Long id;
    @XmlElement(name = "username")
    private String name;
    @XmlElement(name = "contest")
    private ContestXmlDto contest;

    public ProblemlXml() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContestXmlDto getContest() {
        return contest;
    }

    public void setContest(ContestXmlDto contest) {
        this.contest = contest;
    }
}
