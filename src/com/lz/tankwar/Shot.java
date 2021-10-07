package com.lz.tankwar;

public class Shot implements Runnable {
    int x;
    int y;
    int direct = 0;//子弹方向
    int speed = 4;//子弹速度
    boolean isAlive = true;//子弹是否还存活

    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {
        while (true) {
            try {
                //休眠,否则看不清子弹轨迹
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            switch (direct) {
                case 0:
                    y -= speed;
                    break;
                case 1:
                    x += speed;
                    break;
                case 2:
                    y += speed;
                    break;
                case 3:
                    x -= speed;
                    break;
            }
            //System.out.println("x=" + x + " y=" +y);
            //子弹到边界销毁
            if (!(x >= 0 && x <= 1000 && y >= 0 && y<= 800)) {
                isAlive = false;
                System.out.println("超出边界");
                break;
            }

        }
    }
}
