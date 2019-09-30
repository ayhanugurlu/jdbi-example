package com.au.example.jdbiexample.service;

import com.au.example.jdbiexample.exception.NotFoundException;
import com.au.example.jdbiexample.service.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO findById(Integer id) throws NotFoundException;

    String findUserNameById(Integer id) throws NotFoundException;

    List<UserDTO> findAll();

    void save(UserDTO userDTO);

    void saveAll(List<UserDTO> userDTOs);


}
