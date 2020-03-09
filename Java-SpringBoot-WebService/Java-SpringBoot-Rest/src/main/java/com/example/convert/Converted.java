package com.example.convert;


public interface Converted<T, U> {

    public T fromDto(U dto);

}
