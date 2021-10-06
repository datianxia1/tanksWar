package com.lz.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DrawCircle extends JFrame {//框架

    //初始化面板
    private MyPanel mp = null;
    public static void main(String[] args) {
        new DrawCircle();
    }

    public DrawCircle() {
        //初始化面板
        mp = new MyPanel();
        //把面板放入画框中(窗口)
        this.add(mp);
        //指定窗口大小
        this.setSize(400,400);
        //可显示
        this.setVisible(true);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

class MyPanel extends Panel implements KeyListener {
    int x = 10;
    int y = 10;
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(x,y,100,100);
    }

    //有字符输出,事件触发
    @Override
    public void keyTyped(KeyEvent e) {

    }

    //当某个键按下,事件触发
    @Override
    public void keyPressed(KeyEvent e) {

        //System.out.println((char)e.getKeyCode() + "被按下");
        //向下键
        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            y++;
        }
        this.repaint();
    }

    //当某个键松开,事件触发
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
