package com.example.spring.repository;

import com.example.spring.model.Coffee;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeRepository extends CrudRepository<Coffee, Long> {
    Coffee findByName(String name);
}
