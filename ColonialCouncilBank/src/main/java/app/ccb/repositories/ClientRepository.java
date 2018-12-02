package app.ccb.repositories;

import app.ccb.domain.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findByFullName(String fullName);
    @Query(value = "SELECT * FROM clients as cl\n" +
            "join bank_accounts as b\n" +
            "on b.client_id = cl.id\n" +
            "join cards as c\n" +
            "on c.bank_account_id = b.id\n" +
            "group by cl.id\n" +
            "order by count(c.id) desc\n" +
            "limit 1;", nativeQuery = true)
    Client query2();
}
