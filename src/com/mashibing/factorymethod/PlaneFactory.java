package com.mashibing.factorymethod;

public class PlaneFactory {
    public Moveble create(){
        System.out.println("a plane created！");
        return new Plane();
    }
}
