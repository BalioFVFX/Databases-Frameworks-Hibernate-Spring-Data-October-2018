package app.domain.entity;

import app.domain.entity.base.BaseEntity;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity(name = "users")
public class User extends BaseEntity {
    private String username;
    private Set<Problem> problems;
    private List<Submission> submissions;
    private List<Contest> contests;
    private Set<MaximumResultsForContest> maximumResultsForContest;

    public User() {
    }


    @Column(unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @ManyToMany(targetEntity = Problem.class, fetch = FetchType.EAGER)
    @JoinTable(name = "users_problems",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "problem_id", referencedColumnName = "id"))
    public Set<Problem> getProblems() {
        return problems;
    }

    public void setProblems(Set<Problem> problems) {
        this.problems = problems;
    }

    @OneToMany(targetEntity = Submission.class, mappedBy = "contestant")
    public List<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<Submission> submissions) {
        this.submissions = submissions;
    }

    @ManyToMany(targetEntity = Contest.class, fetch = FetchType.EAGER)
    @JoinTable(name = "users_participations",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "contest_id", referencedColumnName = "id"))
    public List<Contest> getContests() {
        return contests;
    }

    public void setContests(List<Contest> contests) {
        this.contests = contests;
    }

    @OneToMany(targetEntity = MaximumResultsForContest.class, mappedBy = "contestant", fetch = FetchType.EAGER)
    public Set<MaximumResultsForContest> getMaximumResultsForContest() {
        return maximumResultsForContest;
    }

    public void setMaximumResultsForContest(Set<MaximumResultsForContest> maximumResultsForContest) {
        this.maximumResultsForContest = maximumResultsForContest;
    }
}
