package com.au.example.jdbiexample.repository;

import com.au.example.jdbiexample.repository.model.User;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.PreparedBatch;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {


    private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS USERS(ID INT PRIMARY KEY, USERNAME VARCHAR(255));";
    private static final String INSERT_USER_QUERY = "INSERT INTO users(id, username) VALUES (:id, :username);";
    private static final String SELECT_USERS_QUERY = "SELECT id, username FROM users";
    private static final String SELECT_USER_QUERY = "SELECT id, username FROM users WHERE id=:id";
    private static final String SELECT_USERNAME_QUERY = "SELECT username FROM users WHERE id=:id";

    private final Jdbi jdbi;

    UserRepositoryImpl(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public void createTable() {
        jdbi.withHandle(handle ->
                handle.execute(CREATE_TABLE_QUERY));
    }

    @Override
    public List<User> findAll() {
        return jdbi.withHandle(handle ->
                handle.createQuery(SELECT_USERS_QUERY)
                        .mapTo(User.class)).list();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return jdbi.withHandle(handle ->
                handle.createQuery(SELECT_USER_QUERY)
                        .bind("id", id)
                        .mapTo(User.class)).findFirst();
    }

    @Override
    public Optional<String> findUserNameById(Integer id) {
        return jdbi.withHandle(handle ->
                handle.createQuery(SELECT_USER_QUERY)
                        .bind("id", id)
                        .mapTo(String.class)).findFirst();
    }



    @Override
    public User save(User user) {
        jdbi.useHandle(handle ->
                handle.createUpdate(INSERT_USER_QUERY)
                        .bind("id", user.getId())
                        .bind("username", user.getUsername()).execute());
        return user;
    }

    @Override
    public List<User> saveAll(List<User> orders) {
        jdbi.useHandle(handle -> {
            PreparedBatch preparedBatch =
                    handle.prepareBatch(INSERT_USER_QUERY);
            orders.forEach(order -> preparedBatch
                    .bind("id", order.getId())
                    .bind("username", order.getUsername()).add());
            preparedBatch.execute();
        });
        return orders;
    }
}
