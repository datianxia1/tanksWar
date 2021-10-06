package com.lz.test.runnable_;

public class RunnableExercise02 {
    public static void main(String[] args) {
        Thread01 thread01 = new Thread01();
        thread01.start();

        Thread02 thread02 = new Thread02();
        Thread thread = new Thread(thread02);
        thread.start();
    }
}

class Thread01 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("hello world");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Thread02 implements Runnable {

    int count;
    @Override
    public void run() {
        while(true) {
            System.out.println("hi");
            count++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count == 50) {
                break;
            }
        }
    }
}
