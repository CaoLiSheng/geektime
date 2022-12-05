package com.example.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @author youxin
 */
@Data
@ToString
@AllArgsConstructor
public class CarDto {
    private String make;
    private int seatCount;
}
