import entities.User;
import orm.Connector;
import orm.EntityManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Application {
    public static void main(String[] args) throws SQLException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        Connector.createConnection("root", "123456", "test");
        Connection connection = Connector.getConnection();
        EntityManager<User> entityManager = new EntityManager<User>(connection);

        User user1 = new User("Pesho-New", 10);
        user1.setId(1);

        Iterable<User> pesho = entityManager.find(User.class);

        pesho.forEach(p -> {
            System.out.println(p.getUsername());
        });
    }
}
