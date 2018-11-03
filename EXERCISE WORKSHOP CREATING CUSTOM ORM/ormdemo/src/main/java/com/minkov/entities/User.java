package com.minkov.entities;

import com.minkov.db.annotations.Column;
import com.minkov.db.annotations.Entity;
import com.minkov.db.annotations.PrimaryKey;

@Entity(name = "employees")
public class User {
    @PrimaryKey(name = "id")
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "ucn")
    private String ucn;

    @Column(name = "age")
    private int age;

    public User() {

    }

    public User(String firstName, String lastName, int age, String ucn) {
        setFirstName(firstName);
        setLastName(lastName);
        setAge(age);
        setUcn(ucn);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("%d | %s | %s",
                getId(), getFirstName(), getLastName());
    }

    public String getUcn() {
        return ucn;
    }

    public void setUcn(String ucn) {
        if(ucn.equals("")){
            this.ucn = "null";
        }
        else{
            this.ucn = ucn;
        }

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
