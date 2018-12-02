package app.domain.entity;

import app.domain.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name = "max_results_for_contests")
public class MaximumResultsForContest extends BaseEntity {
    private Double averagePerformance;
    private Double overallPoints;
    private Contest contest;
    private User contestant;


    public MaximumResultsForContest() {
    }

    @Column(name = "average_performance")
    public Double getAveragePerformance() {
        return averagePerformance;
    }

    public void setAveragePerformance(Double averagePerformance) {
        this.averagePerformance = averagePerformance;
    }

    @Column(name = "overall_points")
    public Double getOverallPoints() {
        return overallPoints;
    }

    public void setOverallPoints(Double overallPoints) {
        this.overallPoints = overallPoints;
    }

    @ManyToOne(targetEntity = Contest.class)
    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    @ManyToOne(targetEntity = User.class)
    public User getContestant() {
        return contestant;
    }

    public void setContestant(User contestant) {
        this.contestant = contestant;
    }
}
