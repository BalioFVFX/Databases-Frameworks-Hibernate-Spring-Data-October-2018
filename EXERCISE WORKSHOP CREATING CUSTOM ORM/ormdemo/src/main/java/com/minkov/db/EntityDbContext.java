package com.minkov.db;

import com.minkov.db.annotations.Column;
import com.minkov.db.annotations.Entity;
import com.minkov.db.annotations.PrimaryKey;
import com.minkov.db.base.DbContext;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EntityDbContext<T> implements DbContext<T> {
    private static final String SELECT_QUERY_TEMPLATE = "SELECT * FROM {0}";
    private static final String SELECT_WHERE_QUERY_TEMPLATE = "SELECT * FROM {0} WHERE {1}";
    private static final String SELECT_SINGLE_QUERY_TEMPLATE = "SELECT * FROM {0} LIMIT 1";
    private static final String SELECT_SINGLE_WHERE_QUERY_TEMPLATE = "SELECT * FROM {0} WHERE {1} LIMIT 1";
    private static final String INSERT_QUERY_TEMPLATE = "INSERT INTO {0}({1}) VALUES({2})";
    private static final String UPDATE_QUERY_TEMPLATE = "UPDATE {0} SET {1} WHERE {2}={3}";

    private static final String SET_QUERY_TEMPLATE = "{0}={1}";
    private static final String WHERE_PRIMARY_KEY = " {0}={1} ";
    private final Connection connection;
    private final Class<T> klass;

    public EntityDbContext(Connection connection, Class<T> klass) throws Exception {
        this.connection = connection;
        this.klass = klass;

        if(tableExists()){
            this.updateTable();
        }
        else{
            this.createTable();
        }

    }

    private void updateTable() throws SQLException {
        List<String> databaseColumnNames = getDatabaseColumnNames();
        List<Field> entityColumnFields = this.getColumnFields();

        List<String> missingColumns = new ArrayList<String>();
        entityColumnFields.forEach(entityColumnField -> {
            String columnName = entityColumnField.getAnnotation(Column.class).name();
            if(!databaseColumnNames.contains(columnName)){
                try {
                    missingColumns.add("ADD " + columnName + " " + this.getDataType(entityColumnField));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        if(missingColumns.size() > 0){
            String sql = String.format("ALTER TABLE %s ", this.getTableName());
            sql += String.join(",", missingColumns);

            connection.prepareStatement(sql).execute();
        }
    }

    private List<String> getDatabaseColumnNames() throws SQLException {
        String sql = String
                .format(
                        "SELECT COLUMN_NAME FROM information_schema.COLUMNS WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = '%s' AND COLUMN_NAME <> 'id' ",
                        this.getTableName()
                );
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<String> columnNames = new ArrayList<String>();

        while(resultSet.next()){
            columnNames.add(resultSet.getString(1));
        }
        return columnNames;
    }

    private boolean createTable() throws Exception {
        String primaryKey = String.format("%s %s PRIMARY KEY AUTO_INCREMENT", this.getPrimaryKeyField().getAnnotation(PrimaryKey.class).name(), this.getDataType(this.getPrimaryKeyField()));
        String columns = "";
        List<String> columnFields = new ArrayList<String>();

        this.getColumnFields().forEach(field -> {
            try {
                columnFields.add(field.getAnnotation(Column.class).name() + " " + this.getDataType(field));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        columns += String.join(", ", columnFields);

        String sql = String.format("CREATE TABLE %s (%s, %s)", getTableName(), primaryKey, columns);

        return connection.prepareStatement(sql).execute();

    }

    private boolean tableExists() throws SQLException {
        String sql = String.format("SELECT TABLE_NAME FROM information_schema.TABLES WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = '%s'",
                this.getTableName());

        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        // Returns true if there's a record
        return resultSet.next();
    }

    // Add & Update
    public boolean persist(T entity) throws IllegalAccessException, SQLException {
        Field primaryKeyField = getPrimaryKeyField();
        primaryKeyField.setAccessible(true);
        long primaryKey = (long) primaryKeyField.get(entity);
        if (primaryKey > 0) {
            return update(entity);
        }

        return insert(entity);
    }

    public boolean delete(String where) throws SQLException {
        String sql = String.format("DELETE FROM %s WHERE %s",
                this.getTableName(),
                where);
        return connection.prepareStatement(sql).execute();
    }

    private boolean insert(T entity) throws SQLException {
        List<String> columns = new ArrayList<>();
        List<Object> values = new ArrayList<>();

        getColumnFields()
                .forEach(field -> {
                    try {
                        field.setAccessible(true);
                        String columnName = field.getAnnotation(Column.class)
                                .name();
                        Object value = field.get(entity);
                        columns.add(columnName);
                        values.add(value);

                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });

        String columnsNames = String.join(", ", columns);
        String columnValues = values
                .stream()
                .map(value -> {
                    if (value instanceof String) {
                        return "\'" + value + "\'";
                    }

                    return value;
                })
                .map(Object::toString)
                .collect(Collectors.joining(", "));

        String queryString = MessageFormat.format(
                INSERT_QUERY_TEMPLATE,
                getTableName(),
                columnsNames,
                columnValues
        );

        return connection.prepareStatement(queryString)
                .execute();
    }

    private boolean update(T entity) throws SQLException, IllegalAccessException {
        // UPDATE {0} {1} WHERE {2}={3}
        // {1} -> SET {0}={1},SET {0}={1}

        List<String> updateQueries =
                getColumnFields().stream()
                        .map(field -> {
                            field.setAccessible(true);
                            try {
                                String columnName = field.getAnnotation(Column.class)
                                        .name();
                                Object value = field.get(entity);
                                if (value instanceof String) {
                                    value = "\'" + value + "\'";
                                }

                                return MessageFormat.format(
                                        SET_QUERY_TEMPLATE,
                                        columnName,
                                        value
                                );
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            return null;
                        })
                        .collect(Collectors.toList());

        String updateQueriesString = String.join(", ", updateQueries);

        Field primaryKey = getPrimaryKeyField();
        primaryKey.setAccessible(true);
        String primaryKeyName =
                primaryKey.getAnnotation(PrimaryKey.class)
                        .name();

        long primaryKeyValue =
                (long) primaryKey
                        .get(entity);

        String queryString = MessageFormat.format(
                UPDATE_QUERY_TEMPLATE,
                getTableName(),
                updateQueriesString,
                primaryKeyName,
                primaryKeyValue
        );

        return connection.prepareStatement(queryString)
                .execute();
    }

    public List<T> find() throws SQLException, IllegalAccessException, InstantiationException {
        return find(null);
    }

    public T findFirst() throws SQLException, IllegalAccessException, InstantiationException {
        return find(SELECT_SINGLE_QUERY_TEMPLATE, null)
                .get(0);
    }

    public List<T> find(String where) throws SQLException, IllegalAccessException, InstantiationException {
        String queryTemplate = where == null
                ? SELECT_QUERY_TEMPLATE
                : SELECT_WHERE_QUERY_TEMPLATE;

        return find(queryTemplate, where);
    }

    public T findFirst(String where) throws SQLException, IllegalAccessException, InstantiationException {
        return find(SELECT_SINGLE_WHERE_QUERY_TEMPLATE, where)
                .get(0);
    }

    public T findById(long id) throws IllegalAccessException, SQLException, InstantiationException {
        String primaryKeyName =
                getPrimaryKeyField().getAnnotation(PrimaryKey.class)
                        .name();

        // {0}={1} -> id=4
        String whereString = MessageFormat.format(
                WHERE_PRIMARY_KEY,
                primaryKeyName,
                id
        );
        return findFirst(whereString);
    }

    private String getTableName() {
        Annotation annotation = Arrays.stream(klass.getAnnotations())
                .filter(a -> a.annotationType() == Entity.class)
                .findFirst()
                .orElse(null);

        if (annotation == null) {
            return klass.getSimpleName().toLowerCase() + "s";
        }

        return klass.getAnnotation(Entity.class)
                .name();
    }

    private List<T> find(String template, String where) throws SQLException, IllegalAccessException, InstantiationException {
        String queryString = MessageFormat.format(
                template,
                getTableName(),
                where
        );

        PreparedStatement query = connection.prepareStatement(queryString);
        ResultSet resultSet = query.executeQuery();

        return toList(resultSet);
    }

    private List<T> toList(ResultSet resultSet) throws SQLException, InstantiationException, IllegalAccessException {
        // id | first_name | last_name
        // ---|------------|----------
        // 1  | Pesho      | Peshov
        List<T> result = new ArrayList<>();

        while (resultSet.next()) {
            T entity = this.createEntity(resultSet);
            result.add(entity);
        }

        return result;
    }

    private T createEntity(ResultSet resultSet) throws IllegalAccessException, InstantiationException, SQLException {
        T entity = klass.newInstance();
        List<Field> columnFields = getColumnFields();

        Field primaryKeyField = getPrimaryKeyField();

        primaryKeyField.setAccessible(true);
        String primaryKeyColumnName = primaryKeyField.getAnnotation(PrimaryKey.class)
                .name();
        long primaryKeyValue = resultSet.getLong(primaryKeyColumnName);
        primaryKeyField.set(entity, primaryKeyValue);

        columnFields.forEach(field -> {
            String columnName = field.getAnnotation(Column.class)
                    .name();
            try {
                field.setAccessible(true);
                if (field.getType() == Long.class || field.getType() == long.class) {
                    long value = resultSet.getLong(columnName);
                    field.set(entity, value);
                } else if (field.getType() == String.class) {
                    String value = resultSet.getString(columnName);
                    field.set(entity, value);
                }
            } catch (SQLException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        return entity;
    }

    private List<Field> getColumnFields() {
        return Arrays.stream(klass.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class))
                .collect(Collectors.toList());
    }

    private Field getPrimaryKeyField() {
        return Arrays.stream(klass.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(PrimaryKey.class))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Class " + klass + " does not have a primary key annotation"));
    }

    private String getDataType(Field field) throws Exception {
        if(field.getType() == String.class){
            return "VARCHAR(255)";
        }
        else if(field.getType() == Long.class || field.getType() == long.class || field.getType() == Integer.class || field.getType() == int.class){
            return "INT";
        }
        else{
            throw new Exception("Unknown data type!");
        }
    }
}
