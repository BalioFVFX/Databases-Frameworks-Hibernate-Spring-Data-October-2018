package App.repositories;

import App.models.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional

public interface AccountRepository extends CrudRepository<Account, Long> {
}
