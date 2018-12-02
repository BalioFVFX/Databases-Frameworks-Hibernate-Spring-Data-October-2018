package app.domain.entity;

import app.domain.entity.base.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "users")
public class User extends BaseEntity {
    private String username;
    private List<Problem> problems;
    private List<Submission> submissions;
    private List<Contest> contests;
    private List<MaximumResultsForContest> maximumResultsForContest;

    public User() {
    }


    @Column(unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @ManyToMany(targetEntity = Problem.class)
    @JoinTable(name = "users_problems",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "problem_id", referencedColumnName = "id"))
    public List<Problem> getProblems() {
        return problems;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }

    @OneToMany(targetEntity = Submission.class, mappedBy = "contestant")
    public List<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<Submission> submissions) {
        this.submissions = submissions;
    }

    @ManyToMany(targetEntity = Contest.class, mappedBy = "contestants")
    public List<Contest> getContests() {
        return contests;
    }

    public void setContests(List<Contest> contests) {
        this.contests = contests;
    }

    @OneToMany(targetEntity = MaximumResultsForContest.class, mappedBy = "contestant")
    public List<MaximumResultsForContest> getMaximumResultsForContest() {
        return maximumResultsForContest;
    }

    public void setMaximumResultsForContest(List<MaximumResultsForContest> maximumResultsForContest) {
        this.maximumResultsForContest = maximumResultsForContest;
    }
}
