package com.lz.test.runnable_;

public class Homework02 {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Thread thread0 = new Thread(bank);
        Thread thread1 = new Thread(bank);
        thread0.start();
        thread1.start();
    }
}

class Bank implements Runnable {
    private int money = 10000;
    private boolean loop = true;

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    @Override
    public void run() {
        while (loop) {
            try {
                getMoney();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void getMoney() throws InterruptedException {
        synchronized (this) {
            if (money < 1000) {
                System.out.println("没钱了!!!");
                setLoop(false);
                return;
            }
            money -=1000;
            System.out.println(Thread.currentThread().getName() + "取钱 还剩下" + money);
            Thread.sleep(1000);
        }

    }
}