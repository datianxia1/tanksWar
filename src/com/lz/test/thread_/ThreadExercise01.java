package com.lz.test.thread_;

public class ThreadExercise01 {
    public static void main(String[] args) throws InterruptedException {
        Car car = new Car();
        car.start();

        //System.out.println(car.getPriority());
        //car.setName("wudi");
        //System.out.println("  !!!!!1  " +car.getName());
        System.out.println(Thread.currentThread().getName());
        for (int i = 0; i < 7; i++) {
            System.out.println(i + " " + Thread.currentThread().getName());
            Thread.sleep(1000);
        }

        car.interrupt();
    }
}

class Car extends Thread{
    int count = 0;
    @Override
    public void run() {
        while(true) {
            System.out.println(count++ + "  子线程" + " " + Thread.currentThread().getName() );
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "interrupt");
            }
            if(count == 80) {
                break;
            }
        }
    }
}
//    @Override
//    public synchronized void start() {
//        int count = 0;
//        while(true) {
//            System.out.println(count++ + " ");
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            if (count == 8) {
//                break;
//            }
//        }
//    }

