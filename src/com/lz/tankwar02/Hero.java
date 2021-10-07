package com.lz.tankwar02;

public class Hero extends Tank {

    Shot shot = null;
    public Hero(int x, int y) {
        super(x, y);
    }
    public void shotEnemyTank() {
        switch (getDirect()) {
            case 0://得到hero方向  炮筒向上
                shot = new Shot(getX() +20,getY(),0);
                break;
            case 1:
                shot = new Shot(getX() + 60,getY() + 20,1);
                break;
            case 2:
                shot = new Shot(getX() + 20 ,getY() + 60,2);
                break;
            case 3:
                shot = new Shot(getX(),getY() + 20,3);
                break;
        }
        //线程启动
        new Thread(shot).start();
    }
}
