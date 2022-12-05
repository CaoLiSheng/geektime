package com.example.spring.model;

import lombok.*;

/**
 * @author youxin
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    private String make;
    private int numberOfSeats;
}
