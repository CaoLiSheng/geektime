package com.example.spring.controller;

import com.example.spring.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private MyService myService;

    @GetMapping("/test")
    public String test() {
        myService.addCoffee();
        return "True";
    }

}
