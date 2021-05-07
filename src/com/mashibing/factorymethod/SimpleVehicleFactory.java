package com.mashibing.factorymethod;

/**
 * 简单工厂可扩展性不好
 */
public class SimpleVehicleFactory {

    public Car createCar() {
        return new Car();
    }

    public Plane createPlan(){
        return new Plane();
    }
}
