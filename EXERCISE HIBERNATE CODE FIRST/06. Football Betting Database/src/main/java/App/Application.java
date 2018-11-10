package App;

import Entities.Continent;
import Entities.Country;
import Entities.Player;
import Entities.Team;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;

public class Application {
    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("betting_db").createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.getTransaction().commit();
    }
}
