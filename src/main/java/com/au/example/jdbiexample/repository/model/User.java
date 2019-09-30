package com.au.example.jdbiexample.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class User {
    private Integer id;
    private String username;
}
