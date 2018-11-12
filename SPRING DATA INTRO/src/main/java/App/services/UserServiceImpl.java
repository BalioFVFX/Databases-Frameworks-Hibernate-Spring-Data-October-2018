package App.services;

import App.models.User;
import App.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) {
        User dbUser = userRepository.getByUsername(user.getUsername());
        if(dbUser != null){
            throw new IllegalArgumentException("There is already user with this username in the database!");
        }
        userRepository.save(user);
        System.out.println("User registration completed successfully!");
    }
}
