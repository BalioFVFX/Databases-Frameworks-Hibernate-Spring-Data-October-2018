package app.domain.entity;

import app.domain.entity.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity(name = "max_results_for_problems")
public class UserMaximumResultsForProblem extends BaseEntity {

    private Problem problem;
    private Submission submission;
    private User contestant;


    public UserMaximumResultsForProblem() {
    }

    @ManyToOne(targetEntity = Problem.class)
    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    @ManyToOne(targetEntity = Submission.class)
    public Submission getSubmission() {
        return submission;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
    }

    @ManyToOne(targetEntity = User.class)
    public User getContestant() {
        return contestant;
    }

    public void setContestant(User contestant) {
        this.contestant = contestant;
    }
}
