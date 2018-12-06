package app.domain.dto.importxml.problem;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "problems")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ProblemListXml {
    @XmlElement(name = "problem")
    private List<ProblemlXml> problems;

    public ProblemListXml() {
        this.problems = new ArrayList<>();
    }

    public List<ProblemlXml> getProblems() {
        return problems;
    }

    public void setProblems(List<ProblemlXml> problems) {
        this.problems = problems;
    }
}
