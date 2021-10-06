package com.lz.test.runnable_;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

public class Homework01 {
    public static void main(String[] args) {
        Homework01 homework01 = new Homework01();
       // homework01.addKeyLis
        Print print = new Print();
        Thread threadPrint = new Thread(print);
        threadPrint.start();
        End end = new End(print);
        Thread threadEnd = new Thread(end);
        threadEnd.start();
    }
}

class Print implements Runnable{
    private boolean loop = true;

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    @Override
    public void run() {
        while(loop) {
            System.out.println(Math.random() * 100 );
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class End implements Runnable {

    private Scanner s = new Scanner(System.in);
    private Print p = null;
    public End (Print p) {
        this.p = p;
    }
    @Override
    public void run() {
        while (true) {
            System.out.println("请输入Q");
            char key = s.next().toUpperCase().charAt(0);
            System.out.println("key:" + key);
            if (key == 'Q') {
                System.out.println("输入Q");
                p.setLoop(false);
                break;
            }
        }
    }


}