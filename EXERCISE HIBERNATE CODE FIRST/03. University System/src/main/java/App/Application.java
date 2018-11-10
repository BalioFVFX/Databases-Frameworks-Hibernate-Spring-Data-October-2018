package App;

import Entities.Course;
import Entities.Student;
import Entities.Teacher;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Application {
    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("university_db").createEntityManager();

        entityManager.getTransaction().begin();

        Student student = new Student("Student", "N2");
        Course course = entityManager.createQuery("FROM Course c where c.id = 3", Course.class).getSingleResult();
        student.setCourses(new HashSet<Course>());
        student.getCourses().add(course);
        entityManager.persist(student);

        entityManager.getTransaction().commit();

    }
}
