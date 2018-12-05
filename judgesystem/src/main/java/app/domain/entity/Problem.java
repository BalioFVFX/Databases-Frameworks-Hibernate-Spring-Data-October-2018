package app.domain.entity;

import app.domain.entity.base.BaseEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity(name = "problems")
public class Problem extends BaseEntity {
    private String name;
    private List<Submission> submissions;
    private Set<User> contestants;
    private Contest contest;
    private List<UserMaximumResultsForProblem>  userMaximumResultsForProblems;

    public Problem() {
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(targetEntity = Submission.class, mappedBy = "problem")
    public List<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<Submission> submissions) {
        this.submissions = submissions;
    }
    @ManyToMany(targetEntity = User.class, mappedBy = "problems")
    public Set<User> getContestants() {
        return contestants;
    }

    public void setContestants(Set<User> contestants) {
        this.contestants = contestants;
    }

    @ManyToOne(targetEntity = Contest.class)
    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    @OneToMany(targetEntity = UserMaximumResultsForProblem.class, mappedBy = "problem")
    public List<UserMaximumResultsForProblem> getUserMaximumResultsForProblems() {
        return userMaximumResultsForProblems;
    }

    public void setUserMaximumResultsForProblems(List<UserMaximumResultsForProblem> userMaximumResultsForProblems) {
        this.userMaximumResultsForProblems = userMaximumResultsForProblems;
    }
}
