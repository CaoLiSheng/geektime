package com.example.spring.controller;

import com.example.spring.dto.User;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping(value = "/valid")
public class ValidController {

    @GetMapping("/hi")
    public String hi() {
        return "Hello World";
    }

    @PostMapping("/obj")
    public String obj(@RequestBody @Valid User user) {
        return "Valid="+user;
    }

    @PostMapping("/list")
    public String list(@RequestBody @Valid List<User> users) {
        return "Valid="+users;
    }

    @GetMapping("/get/{id}")
    public String get(@PathVariable("id") @Valid @Range(min = 1, max = 100) Long id) {
        return String.valueOf(id);
    }

}
