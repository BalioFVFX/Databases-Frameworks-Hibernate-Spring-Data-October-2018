package com.minkov;

import com.minkov.db.EntityDbContext;
import com.minkov.db.base.DbContext;
import com.minkov.entities.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
    private static final String CONNECTION_STRING =
            "jdbc:mysql://localhost:3306/soft_uni_simple";

    public static void main(String[] args) throws Exception {
        Connection connection = getConnection();

        DbContext<User> usersDbContext =
                getDbContext(connection, User.class);


        usersDbContext.persist(new User("Pesho", "Peshov", 10, "1234567890"));
        usersDbContext.persist(new User("Pesho #2", "Peshov", 10, "1234567890"));
        usersDbContext.delete("id = 2");
        //connection.close();
    }

    private static <T> DbContext<T> getDbContext(Connection connection, Class<T> klass) throws Exception {
        return new EntityDbContext<>(connection, klass);
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                CONNECTION_STRING,
                "root",
                "123456"
        );
    }
}
