package app.entities;


import app.entities.geographical.Town;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private char[] profilePicture;
    private Date registrationDate;
    private Date lastTimeLoggedIn;
    private Integer age;
    private boolean isDeleted;
    private Town currentlyLivingTown;
    private Town bornTown;
    private Set<User> friends;

    public User(){

    }

    public User(String username, String firstName, String lastName){
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        setFullName(firstName, lastName);
        this.friends = new HashSet<User>();

    }

    @Column
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column
    @PasswordConstraint(minLength = 4,
    maxLength = 30,
    containsDigit = true,
    containsLowercase = true,
    containsUppercase = true,
    containsSpecialSymbol = true,
    message = "Invalid password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column
    @EmailConstraint
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "profile_picture")
    public char[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(char[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Column(name = "registered_on")
    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Column(name = "last_time_logged_in")
    public Date getLastTimeLoggedIn() {
        return lastTimeLoggedIn;
    }

    public void setLastTimeLoggedIn(Date lastTimeLoggedIn) {
        this.lastTimeLoggedIn = lastTimeLoggedIn;
    }

    @Column
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Column(name = "is_deleted")
    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
    @ManyToOne
    @JoinColumn(name = "current_living_town_id")
    public Town getCurrentlyLivingTown() {
        return currentlyLivingTown;
    }

    public void setCurrentlyLivingTown(Town currentlyLivingTown) {
        this.currentlyLivingTown = currentlyLivingTown;
    }

    @ManyToOne
    @JoinColumn(name = "born_town_id")
    public Town getBornTown() {
        return bornTown;
    }

    public void setBornTown(Town bornTown) {
        this.bornTown = bornTown;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Transient
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String firstName, String lastName) {
        this.fullName = firstName + " " + lastName;
    }

    @ManyToMany(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }
}
