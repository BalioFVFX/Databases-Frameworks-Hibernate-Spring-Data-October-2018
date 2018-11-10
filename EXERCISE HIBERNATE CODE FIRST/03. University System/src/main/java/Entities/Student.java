package Entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student extends Person{

    private Double averageGrade;
    private String attendance;
    private Set<Course> courses;


    public Student(){

    }

    public Student(String firstName, String lastName){
        super(firstName, lastName);

    }


    @Column(name = "average_grade")
    public Double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(Double averageGrade) {
        this.averageGrade = averageGrade;
    }

    @Column(name = "attendance")
    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    // TODO Courses


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "students_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

}
