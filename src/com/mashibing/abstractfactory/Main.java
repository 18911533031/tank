package com.mashibing.abstractfactory;

/**
 * 抽象工厂
 */
public class Main {
    public static void main(String[] args) {
        /*Car c = new Car();
        c.go();
        AK47 w = new AK47();
        w.shoot();
        Bread b = new Bread();
        b.printName();*/
        //不同产品 new 新的类 继承AbastactFactory
        //现代世界的人
        AbastractFactory f = new ModernFactory();
        Vehicle c = f.createVehicle();
        c.go();
        Weapon w = f.createWeapon();
        w.shoot();
        Food b = f.createFood();
        b.printName();
        //魔法世界的人
        AbastractFactory f1 = new MagicFactory();
        Vehicle c1 = f1.createVehicle();
        c1.go();
        Weapon w1 = f1.createWeapon();
        w1.shoot();
        Food b1 = f1.createFood();
        b1.printName();
    }
}
