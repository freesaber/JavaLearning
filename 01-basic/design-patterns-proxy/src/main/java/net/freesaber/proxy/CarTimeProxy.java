package net.freesaber.proxy;

public class CarTimeProxy implements Moveable {
    private Moveable m;

    public CarTimeProxy(Moveable m) {
        super();
        this.m = m;
    }

    public void move() {
        long starttime = System.currentTimeMillis();
        System.out.println("汽车开始行驶...");

        m.move();

        long endtime = System.currentTimeMillis();
        System.out.println("汽车行驶结束...汽车行驶时间:" + (endtime - starttime) + "毫秒");
    }
}
