package app.domain.entity;

import app.domain.entity.base.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "strategies")
public class Strategy extends BaseEntity {
    private String name;
    private List<Contest> contests;
    private List<Submission> submissions;

    public Strategy() {
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @ManyToMany(targetEntity = Contest.class, mappedBy = "strategies")
    public List<Contest> getContests() {
        return contests;
    }

    public void setContests(List<Contest> contests) {
        this.contests = contests;
    }

    @OneToMany(targetEntity = Submission.class, mappedBy = "strategy")
    public List<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<Submission> submissions) {
        this.submissions = submissions;
    }
}
