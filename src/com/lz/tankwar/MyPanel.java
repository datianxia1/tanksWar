package com.lz.tankwar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

//将MyPanel继承Runnable实现线程化,可以绘制子弹路径(通过run方法)
public class MyPanel extends JPanel implements KeyListener, Runnable {
    private Hero hero = null;
    //定义敌人tank多个 采用Vector线程安全
    Vector<EnemyTank> enemyTanks = new Vector<>();
    int enemyTankSize = 3;

    public MyPanel() {
        hero = new Hero(100, 100);
        //hero.setSpeed(5);
        for (int i = 0; i < enemyTankSize; i++) {
            //创建一个敌方Tank
            EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
            //敌方Tank方向
            enemyTank.setDirect(2);
            //创建炮弹
            Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
            //加入到shot集合
            enemyTank.shots.add(shot);
            //启动shot对象
            new Thread(shot).start();
            //加入敌方Tank集合
            enemyTanks.add(enemyTank);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 800);
        //画出hero
        drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 0);

        for (EnemyTank enemyTank : enemyTanks) {
            //if (enemyTank.isAlive) {//存活的敌方Tank才被绘制

                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 1);
                for (Shot shot : enemyTank.shots) {
                    if (shot.isAlive) {//活
                        g.draw3DRect(shot.x, shot.y, 1, 1, false);
                    } else {
                        enemyTank.shots.remove(shot);
                    }
                }
            //}
        }
        //绘制子弹
        if (hero.shot != null && hero.shot.isAlive == true) {
            //System.out.println("调用了画子弹");
            g.draw3DRect(hero.shot.x, hero.shot.y, 1, 1, false);
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

    //编写方法判断我方炮弹是否击中敌方Tank
    public static void hitTank(Shot s, EnemyTank enemyTank) {
        switch (enemyTank.getDirect()) {
            case 0:
            case 1:
                if (s.x <enemyTank.getX() + 40 && s.x > enemyTank.getX() &&
                    s.y <enemyTank.getY() +60 && s.y < enemyTank.getY()) {
                    s.isAlive = false;
                    enemyTank.isAlive = false;
                }
                break;
            case 2:
            case 3:
                if (s.x < enemyTank.getX() + 60 && s.x > enemyTank.getX() &&
                        s.y <enemyTank.getY() +40 && s.y < enemyTank.getY()){
                    s.isAlive = false;
                    enemyTank.isAlive = false;
                }
                break;
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
        //如果按下J会发射
        if (e.getKeyCode() == KeyEvent.VK_J) {
            System.out.println("射击");
            hero.shotEnemyTank();

        }
        //让面板重绘
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (hero.shot != null && hero.shot.isAlive) {
                for (EnemyTank enemyTank : enemyTanks) {
                    hitTank(hero.shot, enemyTank);
                }

            }

            this.repaint();
        }
    }
}
