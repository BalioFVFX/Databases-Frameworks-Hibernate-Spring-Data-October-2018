package app.domain.dto.importxml.submission;

import app.domain.dto.importjson.strategy.StrategyJsonDto;
import app.domain.dto.importxml.problem.ProblemlXml;
import app.domain.dto.importxml.userpart.UserXmlDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class SubmissionXml {
    @XmlElement(name = "id")
    private Long id;
    @XmlElement(name = "performance")
    private double performanceMs;
    @XmlElement(name = "user")
    private UserXmlDto user;
    @XmlElement(name = "problem")
    private ProblemlXml problem;
    @XmlElement(name = "points")
    private Double points;
    @XmlElement(name = "strategy")
    private StrategyJsonDto strategy;

    public SubmissionXml() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPerformanceMs() {
        return performanceMs;
    }

    public void setPerformanceMs(double performanceMs) {
        this.performanceMs = performanceMs;
    }

    public UserXmlDto getUser() {
        return user;
    }

    public void setUser(UserXmlDto user) {
        this.user = user;
    }

    public ProblemlXml getProblem() {
        return problem;
    }

    public void setProblem(ProblemlXml problem) {
        this.problem = problem;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public StrategyJsonDto getStrategy() {
        return strategy;
    }

    public void setStrategy(StrategyJsonDto strategy) {
        this.strategy = strategy;
    }
}
