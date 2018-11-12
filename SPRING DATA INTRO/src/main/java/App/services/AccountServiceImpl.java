package App.services;

import App.models.Account;
import App.repositories.AccountRepository;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Primary
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal money, Long id) {
        Account account = accountRepository.findById(id).orElse(null);
        if(account == null){
            throw new IllegalArgumentException("There is no account with that id!");
        }
        if(account.getUser() == null){
            throw new IllegalArgumentException("This account doesn't have a user!");
        }
        if(money.compareTo(account.getBalance()) > 0){
            throw new IllegalArgumentException("No enough balance! Your current balance is: " + account.getBalance());
        }

        account.setBalance(account.getBalance().subtract(money));
        System.out.println("Withdraw completed successfully!");
    }

    @Override
    public void transferMoney(BigDecimal money, Long id) {
        Account account = accountRepository.findById(id).orElse(null);
        if(account == null){
            throw new IllegalArgumentException("There is no account with that id!");
        }
        if(account.getUser() == null){
            throw new IllegalArgumentException("This account doesn't have a user!");
        }
        if(money.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Invalid parameter! Money cannot be negative!");
        }

        account.setBalance(account.getBalance().add(money));
        System.out.println("Transfer completed successfully!");
    }
}
