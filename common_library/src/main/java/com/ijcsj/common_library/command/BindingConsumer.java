package com.ijcsj.common_library.command;

public interface BindingConsumer<T> {
    void call(T t);
}
