package app.services;

import app.entities.User;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public interface UserServices {
    void persist(User user);
    User findById(Long id);
    List<User> findByEmailProvider(String provider);
    void setInactiveUsersByDate(String date) throws ParseException;
    int removeInactiveUsers();
}
