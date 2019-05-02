package com.example.aplication;

public interface CommandHandleInterface<T> {

    public void handle(T command);
}
