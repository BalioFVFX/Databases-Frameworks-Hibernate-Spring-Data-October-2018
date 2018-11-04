package orm;


import entities.User;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class EntityManager<E> implements DbContext<E> {
    private Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }


    @Override
    public boolean persist(E entity) throws IllegalAccessException, SQLException, NoSuchFieldException {
        Field primary = this.getId(entity.getClass());
        primary.setAccessible(true);
        Object value = primary.get(entity);

        if (value == null || (int) value <= 0) {
            return this.doInsert(entity, primary);
        }
        return this.doUpdate(entity, primary);

    }

    private Field getId(Class entity) throws NoSuchFieldException {
        return entity.getDeclaredField("id");
    }

    private boolean doUpdate(E entity, Field primary) throws SQLException, IllegalAccessException {
        String tableName = this.getTableName(entity.getClass());
        String query = "UPDATE " + tableName + " SET ";

        List<String> fieldNames = new ArrayList<>();
        List<String> fieldValues = new ArrayList<>();


        for (Field field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Column.class)) {
                fieldNames.add(field.getName());
                if (field.get(entity) instanceof String) {
                    fieldValues.add("'" + field.get(entity) + "'");
                } else {
                    fieldValues.add(field.get(entity).toString());
                }
            }
        }

        for (int i = 0; i < fieldNames.size(); i++) {
            query += fieldNames.get(i) + " = " + fieldValues.get(i) + ",";
        }
        query = query.substring(0, query.length() - 1);
        primary.setAccessible(true);
        query += " WHERE id = " + primary.get(entity);

        return connection.prepareStatement(query).execute();
    }

    private boolean doInsert(E entity, Field primary) throws SQLException, NoSuchFieldException, IllegalAccessException {
        String tableName = this.getTableName(entity.getClass());
        String query = "INSERT INTO " + tableName;
        List<String> fieldNames = new ArrayList<>();
        List<String> fieldValues = new ArrayList<>();

        entity.getClass().getDeclaredFields();
        for (Field field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Column.class)) {
                fieldNames.add(field.getName());
                System.out.println(field.get(entity));
                if (field.get(entity) instanceof String) {
                    fieldValues.add("'" + field.get(entity) + "'");
                } else {
                    fieldValues.add(field.get(entity).toString());
                }
            }
        }
        query += " (";
        query += String.join(",", fieldNames);
        query += ") VALUES (";
        query += String.join(",", fieldValues);
        query += ")";


        return connection.prepareStatement(query).execute();
    }

    private String getTableName(Class<?> aClass) {
        return aClass.getAnnotation(Entity.class).name();
    }

    @Override
    public Iterable<E> find(Class<E> table) throws IllegalAccessException, SQLException, InstantiationException, NoSuchFieldException {
        return find(table, null);
    }

    @Override
    public Iterable<E> find(Class<E> table, String where) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException {

        if(where == null){
            where = "true";
        }

        String sql = String.format("SELECT * FROM %s WHERE %s", this.getTableName(table), where);
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();
        List<E> list = new ArrayList<E>();
        Field[] fields = table.getDeclaredFields();

        while(resultSet.next()){
            E entity = table.newInstance();
            for (int i = 0; i < fields.length; i++){
                Field currentField = fields[i];
                currentField.setAccessible(true);

                String annotationName = "";
                if(currentField.isAnnotationPresent(Column.class)){
                    annotationName = fields[i].getDeclaredAnnotation(Column.class).name();
                    Field entityField = entity.getClass().getDeclaredField(annotationName);
                    entityField.setAccessible(true);
                    this.fillField(entityField, entity, resultSet, annotationName);
                }
                else{
                    annotationName = currentField.getDeclaredAnnotation(Id.class).name();
                    Field entityField = entity.getClass().getDeclaredField(annotationName);
                    entityField.setAccessible(true);
                    this.fillField(entityField, entity, resultSet, annotationName);
                }
            }


            list.add(entity);

        }
        return list;
    }

    @Override
    public E findFirst(Class<E> table) {
        return null;
    }

    @Override
    public E findFirst(Class<E> table, String where) throws IllegalAccessException, InstantiationException, SQLException {

        Statement stmt  = connection.createStatement();
        String query = "SELECT * FROM " + this.getTableName(table) + " LIMIT 1";
        ResultSet resultSet = stmt.executeQuery(query);
        E entity = table.newInstance();
        resultSet.next();
        this.fillEntity(table, resultSet, entity);
        return entity;
    }

    private void fillEntity(Class<E> table, ResultSet rs, E entity) throws SQLException, IllegalAccessException {
        Field[] fields = table.getDeclaredFields();
        for (int i = 0; i < fields.length; i++){
            Field field = fields[i];
            field.setAccessible(true);
            String name = field.getName();
            this.fillField(field, entity, rs, field.getAnnotation(Column.class).name());
        }
    }

    private void fillField(Field field, Object entity, ResultSet rs, String name) throws SQLException, IllegalAccessException {
        field.setAccessible(true);
        if(field.getType() == int.class || field.getType() == Integer.class){
            field.set(entity, rs.getInt(name));
        }else if(field.getType() == long.class || field.getType() == Long.class){
            field.set(entity, rs.getLong(name));
        }
        else if(field.getType() == String.class){
            field.set(entity, rs.getString(name));
        }
        else if(field.getType() == Date.class){
            field.set(entity, rs.getDate(name));
        }
    }
}
