package com.example.spring.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Data
@ToString
public class User {

    @NotEmpty
    private String name;

}
