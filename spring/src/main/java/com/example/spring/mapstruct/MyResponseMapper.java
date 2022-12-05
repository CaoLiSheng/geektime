package com.example.spring.mapstruct;

import com.example.spring.response.MyResponse;

public interface MyResponseMapper<T,R> extends MyMapper<MyResponse<T>, MyResponse<R>> {

    MyResponse<R> myResponseToMyResponse(MyResponse<T> myResponse);

}
