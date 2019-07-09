package net.freesaber.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TimeHandler implements InvocationHandler {
    private Object target;
    public TimeHandler(Object target) {
        super();
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long starttime = System.currentTimeMillis();
        System.out.println("汽车开始行驶...");

        method.invoke(target);

        long endtime = System.currentTimeMillis();
        System.out.println("汽车行驶结束...汽车行驶时间:" + (endtime - starttime) + "毫秒");
        return null;
    }
}
