package com.lz.tankwar;

import java.util.Vector;

public class EnemyTank extends Tank{
    Vector<Shot> shots = new Vector<>();
    boolean isAlive = true;
    public EnemyTank(int x, int y) {
        super(x, y);
    }
}
