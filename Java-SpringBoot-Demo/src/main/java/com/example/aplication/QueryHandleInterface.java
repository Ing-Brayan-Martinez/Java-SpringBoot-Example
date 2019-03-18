package com.example.aplication;

public interface QueryHandleInterface<I,O> {

    public O handle(I query);

}
