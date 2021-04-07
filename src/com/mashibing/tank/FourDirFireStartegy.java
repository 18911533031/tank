package com.mashibing.tank;

public class FourDirFireStartegy implements FireStartegy{
    @Override
    public void fire(Tank t) {
        //正方形情况下：x+2分1之坦克宽度 - 子弹2分1宽度
        int bX = t.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = t.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;

        Dir[] values = Dir.values();
        for (Dir dir : values) {
            new Bullet(bX + 1, bY + 4, dir, t.getGroup(), t.tf);
        }

        if(t.getGroup() == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }
}
