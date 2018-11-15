package app.services;

import app.entities.User;
import app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.DateFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserServicesImpl implements UserServices {
    private UserRepository userRepository;

    @Autowired
    public UserServicesImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void persist(User user) {
        userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null){
            throw new IllegalArgumentException("No user with id (" + id + ")");
        }
        return user;
    }

    @Override
    public List<User> findByEmailProvider(String provider) {
        provider = "@" + provider;
        return userRepository.findAllByEmailEndingWith(provider);
    }

    @Override
    public void setInactiveUsersByDate(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("d MMM yyyy");
        Date formattedDate = formatter.parse(date);
        this.userRepository.markAllIsDeleted(formattedDate);
    }

    @Override
    public int removeInactiveUsers() {
        return this.userRepository.removeInactiveUsers();
    }
}
