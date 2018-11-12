package App;

import App.models.Account;
import App.models.User;
import App.repositories.AccountRepository;
import App.repositories.UserRepository;
import App.services.AccountService;
import App.services.AccountServiceImpl;
import App.services.UserService;
import App.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@SpringBootApplication
@Component
public class ConsoleRunner implements CommandLineRunner {

    private UserServiceImpl userService;
    private AccountServiceImpl accountService;

    @Autowired
    public ConsoleRunner(UserServiceImpl userService, AccountServiceImpl accountService){
        this.accountService = accountService;
        this.userService = userService;
    }

    @Override
    public void run(String... strings) throws Exception {
        User user = new User("Pesho 6", 25);

        Account account = new Account();
        account.setBalance(new BigDecimal(25000));
        account.setUser(user);

        user.getAccounts().add(account);
        userService.registerUser(user);

    }
}
