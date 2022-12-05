package com.example.spring.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyResponse<T> {

    private Integer code;
    private String message;
    private Boolean success;
    private T data;

}
