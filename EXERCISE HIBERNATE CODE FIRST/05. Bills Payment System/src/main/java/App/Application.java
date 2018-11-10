package App;

import Entities.BankAccount;
import Entities.CreditCard;
import Entities.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Application {
    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("bills_db").createEntityManager();

        entityManager.getTransaction().begin();

        User user = new User("Nov user", "test bank");
        user.setBillingDetail(new BankAccount(user, 1, "Bank", "0001"));

        entityManager.persist(user);
        entityManager.getTransaction().commit();

    }
}
