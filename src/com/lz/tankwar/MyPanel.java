package com.lz.tankwar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener {
    private Hero hero = null;
    //定义敌人tank
    Vector<EnemyTank> enemyTanks = new Vector<>();
    int enemyTankSize = 3;
    public MyPanel() {
        hero = new Hero(100, 100);
        //hero.setSpeed(5);
        for (int i = 0; i < enemyTankSize; i++) {
            EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
            enemyTank.setDirect(2);
            enemyTanks.add(enemyTank);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        System.out.println("11111");
        g.fillRect(0, 0, 1000, 800);
        drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 0);
        for (EnemyTank enemyTank : enemyTanks) {
            drawTank(enemyTank.getX(),enemyTank.getY(),g,enemyTank.getDirect(),1);
        }

    }

    //direct:方向
    //type:类型(敌我)
    //g:画笔
    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        //根据tank的类型设置不同的颜色
        switch (type) {
            case 0://我方tank
                g.setColor(Color.cyan);
                break;
            case 1://敌方tank
                g.setColor(Color.yellow);
                break;
        }
        //设置方向
        switch (direct) {
            //方向向上
            case 0:
                g.fill3DRect(x, y, 10, 60, false);//左轮
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//盖子
                g.fill3DRect(x + 30, y, 10, 60, false);//右轮
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y);
                break;
            //方向向右
            case 1:
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x + 60, y + 20);
                break;
            //方向向下
            case 2:
                g.fill3DRect(x, y, 10, 60, false);//左轮
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//盖子
                g.fill3DRect(x + 30, y, 10, 60, false);//右轮
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y + 60);
                break;

            //方向向左
            case 3:
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x, y + 20);
                break;
            default:
                System.out.println("未设置");
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {//上
            System.out.println("w");
            hero.moveUp();
            hero.setDirect(0);
        } else if (e.getKeyCode() == KeyEvent.VK_D) {//右
            System.out.println("d");
            hero.moveRight();
            hero.setDirect(1);
        } else if (e.getKeyCode() == KeyEvent.VK_S) {//下
            System.out.println("s");
            hero.moveDown();
            hero.setDirect(2);
        } else if (e.getKeyCode() == KeyEvent.VK_A) {//左
            System.out.println("a");
            hero.moveLeft();
            hero.setDirect(3);
        }
        //让面板重绘
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
