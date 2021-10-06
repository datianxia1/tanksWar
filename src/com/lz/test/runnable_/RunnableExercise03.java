package com.lz.test.runnable_;

public class RunnableExercise03 {
    public static void main(String[] args) throws InterruptedException {
        AA aa = new AA();
        Thread thread = new Thread(aa);
        thread.start();

        for (int i = 0; i < 20; i++) {
            System.out.println(Thread.currentThread().getName() + "吃包子" + i);
            Thread.sleep(1000);
            if (i == 5) {
                thread.join();
            }
        }
    }
}

class AA implements Runnable {

    int count = 0;
    @Override
    public void run() {
        while(true) {
            System.out.println(Thread.currentThread().getName()+ "吃包子" + ++count);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count == 10) {
                break;
            }
        }
    }
}
