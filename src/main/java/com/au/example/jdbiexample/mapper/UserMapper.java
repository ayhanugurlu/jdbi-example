package com.au.example.jdbiexample.mapper;

import com.au.example.jdbiexample.repository.model.User;
import com.au.example.jdbiexample.service.dto.UserDTO;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;


@Component
public class UserMapper extends ConfigurableMapper {

    protected void configure(MapperFactory factory) {
        factory.classMap(User.class, UserDTO.class)
                .byDefault()
                .register();

        factory.classMap(UserDTO.class, User.class)
                .byDefault()
                .register();
    }
}
