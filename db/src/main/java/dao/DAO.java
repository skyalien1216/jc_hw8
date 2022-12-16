package dao;

import java_courses.CREDS;
import org.jetbrains.annotations.NotNull;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public abstract class DAO<T> {

    @NotNull T get(int id){
        return null;
    }

    @NotNull T get(String id){
        return null;
    }

    abstract @NotNull List<T> all();

    abstract void save(@NotNull T entity);

    abstract void update(@NotNull T entity);

    abstract void delete(@NotNull T entity);

    protected Connection getConnection() throws SQLException {
       return DriverManager.getConnection("jdbc:postgresql://localhost/" + CREDS.dbName, CREDS.user, CREDS.password);
    }

    protected DSLContext getContext() {
        try {
            return DSL.using(getConnection(), SQLDialect.POSTGRES);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
