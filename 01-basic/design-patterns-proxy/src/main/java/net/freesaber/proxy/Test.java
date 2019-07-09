package net.freesaber.proxy;

public class Test {
    public static void main(String[] args) {
        Car car = new Car();
        CarTimeProxy carTimeProxy = new CarTimeProxy(car);
        CarLogProxy carLogProxy = new CarLogProxy(carTimeProxy);
        carLogProxy.move();
    }
}
