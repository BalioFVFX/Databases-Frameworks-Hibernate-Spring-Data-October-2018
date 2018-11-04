package entities;

import orm.Column;
import orm.Entity;
import orm.Id;

import java.util.Date;

@Entity(name = "users")
public class User {
    @Id(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "age")
    private int age;


    public User(String username, int age){
        this.username = username;
        this.age = age;
    }


    public User(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
