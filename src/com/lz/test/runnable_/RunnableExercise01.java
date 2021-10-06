package com.lz.test.runnable_;

public class RunnableExercise01 {
    public static void main(String[] args) {
        B b = new B();
        Proxy proxy = new Proxy(b);
        proxy.start();
    }
}

class A {}
class B extends A implements Runnable{
    @Override
    public void run() {
        System.out.println("11111");
    }
}


class Proxy implements Runnable {
    private Runnable r= null;
    @Override
    public void run() {
        r.run();
    }
    public Proxy(Runnable r) {
        this.r = r;
    }

    public void start() {
        start0();
    }

    public void start0() {
        run();
    }
}
