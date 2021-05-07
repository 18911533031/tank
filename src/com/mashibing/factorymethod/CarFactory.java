package com.mashibing.factorymethod;

public class CarFactory {

    public Moveble create(){
        System.out.println("a car created！");
        return new Car();
    }
}
