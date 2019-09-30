package com.au.example.jdbiexample.service;

import com.au.example.jdbiexample.exception.NotFoundException;
import com.au.example.jdbiexample.repository.UserRepository;
import com.au.example.jdbiexample.repository.model.User;
import com.au.example.jdbiexample.service.dto.UserDTO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    @NonNull
    private UserRepository userRepository;

    @NonNull
    @Qualifier("userMapper")
    private ConfigurableMapper userMapperFactory;

    public UserDTO findById(Integer id) throws NotFoundException {
        return userRepository.findById(id).map(user -> userMapperFactory.map(user, UserDTO.class)).orElseThrow(() -> new NotFoundException("user not found"));
    }


    public String findUserNameById(Integer id) throws NotFoundException {
        return userRepository.findUserNameById(id).orElseThrow(() -> new NotFoundException("user not found"));
    }


    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(user -> userMapperFactory.map(user, UserDTO.class)).collect(Collectors.toList());
    }


    public void save(UserDTO userDTO) {
        userRepository.save(userMapperFactory.map(userDTO, User.class));
    }


    public void saveAll(List<UserDTO> userDTOs) {
        userRepository.saveAll(userDTOs.stream().map(userDTO -> userMapperFactory.map(userDTO, User.class)).collect(Collectors.toList()));
    }


}
