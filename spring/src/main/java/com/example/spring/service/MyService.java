package com.example.spring.service;

import com.example.spring.exception.MyException;
import com.example.spring.model.Coffee;
import com.example.spring.model.CoffeeOrder;
import com.example.spring.repository.CoffeeOrderRepository;
import com.example.spring.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class MyService {

    @Autowired
    private CoffeeRepository coffeeRepository;
    @Autowired
    private CoffeeOrderRepository coffeeOrderRepository;

    @Lazy
    @Autowired
    private MyService myService;

    @Transactional
    public void addCoffee() {
        coffeeRepository.save(Coffee.builder().name("我的咖啡").price(1000L).build());
        try {
            myService.orderLatte();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new MyException("");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void orderLatte() {
        Coffee latte = coffeeRepository.findByName("latte");
        coffeeOrderRepository.save(CoffeeOrder.builder().items(Collections.singletonList(latte)).customer("小沫沫").state(0).build());
    }

}
