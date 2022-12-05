package com.example.spring;

import com.example.spring.dto.CarDto;
import com.example.spring.mapstruct.CarListMapper;
import com.example.spring.mapstruct.CarMapper;
import com.example.spring.mapstruct.Holder;
import com.example.spring.mapstruct.ResponseCarMapper;
import com.example.spring.model.Car;
import com.example.spring.response.MyResponse;
import com.example.spring.util.HttpHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).web(WebApplicationType.SERVLET).run(args);
    }

    @Autowired
    private HttpHelper httpHelper;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("+++++++++++++++++++++++++++简单类型");

        Car car = Car.builder().make("VGV").numberOfSeats(7).build();
        System.out.println(car);
        System.out.println(httpHelper.transform(new Holder(car),
                new ParameterizedTypeReference<CarDto>() {},
                CarMapper.INSTANCE).getData());

        System.out.println("+++++++++++++++++++++++++++复杂类型");

        MyResponse<Car> response = MyResponse.<Car>builder()
                .code(200)
                .success(true)
                .message("")
                .data(car)
                .build();
        System.out.println(response);
        System.out.println(httpHelper.transform(new Holder(response),
                new ParameterizedTypeReference<MyResponse<CarDto>>() {},
                ResponseCarMapper.INSTANCE).getData());

        System.out.println("+++++++++++++++++++++++++++列表类型");

        MyResponse<List<Car>> carList = MyResponse.<List<Car>>builder()
                .code(200)
                .success(true)
                .message("")
                .data(Collections.singletonList(car))
                .build();
        System.out.println(carList);
        System.out.println(httpHelper.transform(new Holder(carList),
                new ParameterizedTypeReference<MyResponse<List<CarDto>>>() {},
                CarListMapper.INSTANCE).getData());

        System.out.println("+++++++++++++++++++++++++++整合RestTemplate");
        MyResponse<CarDto> exchange = httpHelper.exchange("http://localhost:8080/car",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<MyResponse<CarDto>>() {
                }, null);
        System.out.println(exchange);
    }
}
