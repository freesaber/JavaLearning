package net.freesaber.proxy;

public class CarLogProxy implements Moveable{
    private Moveable m;

    public CarLogProxy(Moveable m) {
        super();
        this.m = m;
    }

    public void move() {
        System.out.println("日志开始...");

        m.move();

        System.out.println("日志结束...");
    }
}
