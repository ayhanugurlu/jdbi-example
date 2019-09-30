package com.au.example.jdbiexample.repository;

import com.au.example.jdbiexample.repository.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void createTable();

    List<User> findAll();

    Optional<User> findById(Integer id);

    Optional<String> findUserNameById(Integer id);

    User save(User order);

    List<User> saveAll(List<User> orders);

}
