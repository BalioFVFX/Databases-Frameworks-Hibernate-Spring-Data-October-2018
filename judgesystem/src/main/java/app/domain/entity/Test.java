package app.domain.entity;

import app.domain.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name = "tests")
public class Test extends BaseEntity {
    private String expectedResult;
    private String testContent;
    private Problem problem;

    public Test() {
    }

    @Column(name = "expected_result")
    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    @Column(name = "test_content")
    public String getTestContent() {
        return testContent;
    }

    public void setTestContent(String testContent) {
        this.testContent = testContent;
    }

    @ManyToOne(targetEntity = Problem.class)
    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }
}
