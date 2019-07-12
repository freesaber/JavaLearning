package net.freesaber.jdkproxy;

import net.freesaber.proxy.Car;
import net.freesaber.proxy.Moveable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {

        Car car = new Car();
        Class<?> cls = car.getClass();

        InvocationHandler timeHandler = new TimeHandler(car);
        Moveable timeMoveable = (Moveable) Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), timeHandler);

        InvocationHandler logHandler = new LogHandler(timeMoveable);
        Moveable logMoveable = (Moveable) Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), logHandler);

        logMoveable.move();
    }
}
