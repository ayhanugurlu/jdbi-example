package com.au.example.jdbiexample.web;

import com.au.example.jdbiexample.exception.NotFoundException;
import com.au.example.jdbiexample.service.UserService;
import com.au.example.jdbiexample.service.dto.UserDTO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    @NonNull
    private final UserService userService;


    @GetMapping(path = "/find/{id}")
    public UserDTO findById(@PathVariable Integer id) throws NotFoundException {
        return userService.findById(id);
    }

    @GetMapping(path = "/findUsername/{id}")
    public String findUsernameById(@PathVariable Integer id) throws NotFoundException {
        return userService.findUserNameById(id);
    }


    @GetMapping(path = "/findAll")
    public List<UserDTO> findAll(){
        return userService.findAll();
    }


    @PostMapping(path = "/save")
    public void save(@RequestBody UserDTO userDTO) {
        userService.save(userDTO);
    }


    @PostMapping(path = "saveAll")
    public void findById(@RequestBody List<UserDTO> userDTOs) {
        userService.saveAll(userDTOs);
    }



}
