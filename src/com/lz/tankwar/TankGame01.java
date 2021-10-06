package com.lz.tankwar;

import javax.swing.*;

public class TankGame01 extends JFrame {
    MyPanel mp = null;

    public static void main(String[] args) {
        new TankGame01();
    }

    public TankGame01() {
        mp = new MyPanel();
        this.add(mp);
        this.setSize(1000,800);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
