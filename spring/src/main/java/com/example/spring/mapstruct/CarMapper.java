package com.example.spring.mapstruct;

import com.example.spring.dto.CarDto;
import com.example.spring.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper extends MyMapper<Car, CarDto> {

    CarMapper INSTANCE = Mappers.getMapper( CarMapper.class );

    @Mapping(source = "make", target = "make")
    @Mapping(source = "numberOfSeats", target = "seatCount")
    CarDto carToCarDto(Car car);
}
