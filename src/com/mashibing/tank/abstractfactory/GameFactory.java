package com.mashibing.tank.abstractfactory;

import com.mashibing.tank.Dir;
import com.mashibing.tank.Group;
import com.mashibing.tank.TankFrame;

public abstract class GameFactory {
    //坦克
    public abstract BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tankFrame);
    //爆炸
    public abstract BaseExplode createExplode(int x, int y, TankFrame tankFrame);
    //子弹
    public abstract BaseBullet createBullet(int x, int y, TankFrame tankFrame);
}
