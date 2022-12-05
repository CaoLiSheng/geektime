package com.example.spring.mapstruct;

import org.mapstruct.Mapping;

/**
 * @author youxin
 */
public interface MyMapper<T, R> {

    default Holder<R> objectToHolder(Object obj) {
        if (obj instanceof Holder) {
            return holderToHolder((Holder<T>) obj);
        }
        return null;
    }

    @Mapping(source = "data", target = "data")
    Holder<R> holderToHolder(Holder<T> response);

}
