package app.domain.entity;

import app.domain.entity.base.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "contests")
public class Contest extends BaseEntity {

    private String name;
    private Category category;
    private List<Problem> problems;
    private List<User> contestants;
    private List<MaximumResultsForContest> maximumResultsForContests;
    private List<Strategy> strategies;


    public Contest() {
    }

    @Column(unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(targetEntity = Category.class)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @OneToMany(targetEntity = Problem.class, mappedBy = "contest")
    public List<Problem> getProblems() {
        return problems;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }

    @ManyToMany(targetEntity = User.class)
    @JoinTable(name = "users_participations",
    joinColumns = @JoinColumn(name = "contest_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    public List<User> getContestants() {
        return contestants;
    }

    public void setContestants(List<User> contestants) {
        this.contestants = contestants;
    }
    @OneToMany(targetEntity = MaximumResultsForContest.class, mappedBy = "contest")
    public List<MaximumResultsForContest> getMaximumResultsForContests() {
        return maximumResultsForContests;
    }

    public void setMaximumResultsForContests(List<MaximumResultsForContest> maximumResultsForContests) {
        this.maximumResultsForContests = maximumResultsForContests;
    }

    @ManyToMany(targetEntity = Strategy.class)
    @JoinTable(name = "contests_strategies",
            joinColumns = @JoinColumn(name = "contest_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "strategy_id", referencedColumnName = "id"))
    public List<Strategy> getStrategies() {
        return strategies;
    }

    public void setStrategies(List<Strategy> strategies) {
        this.strategies = strategies;
    }
}
