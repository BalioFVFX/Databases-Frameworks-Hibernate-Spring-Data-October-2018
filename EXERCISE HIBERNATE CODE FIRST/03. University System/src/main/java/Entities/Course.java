package Entities;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {
    private int id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private Double credits;
    private Set<Student> students;
    private Teacher currentTeacher;


    public Course() {
    }

    public Course(String name, String description, Double credits) {
        this.name = name;
        this.description = description;
        this.credits = credits;
        this.students = new HashSet<Student>();
    }

    @ManyToOne(targetEntity = Teacher.class, cascade = CascadeType.ALL)
    public Teacher getCurrentTeacher() {
        return currentTeacher;
    }

    public void setCurrentTeacher(Teacher currentTeacher) {
        this.currentTeacher = currentTeacher;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "start_date")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Column(name = "end_date")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column
    public Double getCredits() {
        return credits;
    }

    public void setCredits(Double credits) {
        this.credits = credits;
    }

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "courses")
    public Set<Student> getStudents() {
        return students;
    }


    public void setStudents(Set<Student> students) {
        this.students = students;
    }

}
