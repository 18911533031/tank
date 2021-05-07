package com.mashibing.factorymethod;

/**
 * 简单工厂
 */
public class Main {

    public static void main(String[] args) {
        //普通使用对象和方法
//        Car c = new Car();
//        c.go();
//        Plane p = new Plane();
//        p.go();

        //接口实现方法
//        Moveble m = new Plane();
//        m.go();
//        Moveble m = new Car();
//        m.go();

        //单一工厂，如果有新增需要修改原来代码
//        SimpleVehicleFactory v = new SimpleVehicleFactory();
//        Car car = v.createCar();
//        car.go();

        //单独工厂，专门生产固定对象(任意定制交通工具（实现Moveble），任意定制生产过程(单独的Factory))
//        Moveble m = new CarFactory().create();
//        m.go();
//        Moveble m1 = new PlaneFactory().create();
//        m.go();

        //
    }
}
