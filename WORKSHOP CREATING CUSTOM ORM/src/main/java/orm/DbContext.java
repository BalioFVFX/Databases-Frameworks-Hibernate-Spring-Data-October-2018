package orm;

import java.sql.SQLException;

public interface DbContext<E> {
    boolean persist(E entity) throws IllegalAccessException, SQLException, NoSuchFieldException;
    Iterable<E> find(Class<E> table) throws IllegalAccessException, SQLException, InstantiationException, NoSuchFieldException;
    Iterable<E> find(Class<E> table, String where) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException;
    E findFirst(Class<E> table);
    E findFirst(Class<E> table, String where) throws IllegalAccessException, InstantiationException, SQLException;
}
