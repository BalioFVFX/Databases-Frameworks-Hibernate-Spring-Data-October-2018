package app;

import app.entities.User;
import app.services.UserServices;
import app.services.UserServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@Component
public class Run implements CommandLineRunner {

    private UserServicesImpl userServices;

    @Autowired
    public Run(UserServicesImpl userServices) {
        this.userServices = userServices;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setEmail("pesho@gmail.com");
        user.setPassword("Paro0l@");
        SimpleDateFormat formatter = new SimpleDateFormat("d MMM yyyy");
        Date date = formatter.parse("1 Oct 2004");
        user.setLastTimeLoggedIn(date);
        userServices.persist(user);
        userServices.setInactiveUsersByDate("1 Oct 2004");


        int removedUsers = userServices.removeInactiveUsers();

        if(removedUsers <= 0){
            System.out.println("No users have been deleted");
        }
        else{
            System.out.println(removedUsers + " users have been deleted");
        }


    }
}
