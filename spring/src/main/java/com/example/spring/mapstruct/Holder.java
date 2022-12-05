package com.example.spring.mapstruct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @author youxin
 */
@Data
@ToString(callSuper = true)
@AllArgsConstructor
public class Holder<T> {
    private T data;
}
