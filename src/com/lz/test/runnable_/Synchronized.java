package com.lz.test.runnable_;

public class Synchronized {
    public static void main(String[] args) {
//        T t = new T();
//        Thread thread1 = new Thread(t);
//        Thread thread2 = new Thread(t);
//        Thread thread3 = new Thread(t);
//
//        thread1.start();
//        thread2.start();
//        thread3.start();

        T t3 = new T();
        T t1 = new T();
        T t2 = new T();

        t1.start();
        t2.start();
        t3.start();

    }
}

class T extends Thread {
    private static int count = 100;
    @Override
    public void run() {
        while (true) {
            sell();
        }
    }

    public synchronized void sell() {
        if (count <= 0) {
            System.out.println("没有票了");
            return;
        }
        System.out.println(Thread.currentThread().getName() + "还剩" + --count);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

//class T implements Runnable {
//    private static int count = 100;
//    @Override
//    public void run() {
//        while (true) {
//            sell();
//        }
//    }
//
//    public   void sell() {
//        if (count <= 0) {
//            System.out.println("没有票了");
//            return;
//        }
//        System.out.println(Thread.currentThread().getName() + "还剩" + --count);
//        try {
//            Thread.sleep(50);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
////    public synchronized void sell() {
////        if (count > 0) {
////            System.out.println(Thread.currentThread().getName() + "还剩" + --count);
////            return;
////        } else {
////            System.out.println("票卖完了");
////            return;
////        }
////
////
////    }
//}
