package App;

import Entities.Customer;
import Entities.Product;
import Entities.Sale;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class App {
    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("sales_database").createEntityManager();

        entityManager.getTransaction().begin();


        entityManager.getTransaction().commit();
    }
}
