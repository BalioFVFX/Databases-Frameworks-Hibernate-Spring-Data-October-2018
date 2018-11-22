package app.service;

import app.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void save(User user);
    User loginUser(String email, String password);
}
