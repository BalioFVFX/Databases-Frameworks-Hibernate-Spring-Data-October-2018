package app.domain.entity;

import app.domain.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name = "submissions")
public class Submission extends BaseEntity {
    private User contestant;
    private Strategy strategy;
    private Double performanceMs;
    private Double points;
    private Problem problem;

    public Submission() {
    }

    @ManyToOne(targetEntity = User.class)
    public User getContestant() {
        return contestant;
    }

    public void setContestant(User contestant) {
        this.contestant = contestant;
    }

    @ManyToOne(targetEntity = Strategy.class)
    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    @Column(name = "performance")
    public Double getPerformanceMs() {
        return performanceMs;
    }

    public void setPerformanceMs(Double performanceMs) {
        this.performanceMs = performanceMs;
    }

    @Column
    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    @ManyToOne(targetEntity = Problem.class)
    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }
}
