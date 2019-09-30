package com.au.example.jdbiexample.listener;

import com.au.example.jdbiexample.repository.UserRepository;
import com.au.example.jdbiexample.repository.model.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ApplicationStartupListener implements ApplicationListener<ContextRefreshedEvent> {


    @NonNull UserRepository userRepository;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        userRepository.createTable();
        userRepository.save(User.builder().id(1).username("ayhan").build());

    }
}