package com.example.spring.mapstruct;

import com.example.spring.dto.CarDto;
import com.example.spring.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CarListMapper extends MyResponseMapper<List<Car>, List<CarDto>> {

    CarListMapper INSTANCE = Mappers.getMapper( CarListMapper.class );

    @Mapping(source = "make", target = "make")
    @Mapping(source = "numberOfSeats", target = "seatCount")
    CarDto carToCarDto(Car car);

}
