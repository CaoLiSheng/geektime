package com.example.spring.controller;

import com.example.spring.model.Car;
import com.example.spring.response.MyResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car")
public class CarController {

    @GetMapping()
    public MyResponse<Car> car() {
        return MyResponse.<Car>builder()
                .code(200)
                .success(true)
                .message("OK")
                .data(Car.builder().make("华为电动车").numberOfSeats(5).build())
                .build();
    }

}
