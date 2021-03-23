package com.mashibing.tank;

import java.awt.*;

public class Tank {

    private int x, y;

    /**
     * 方向
     */
    private Dir dir = Dir.UP;

    /**
     * 移动速度
     */
    private static final int SPEED = 3;

    /**
     * 图片的宽高
     */
    public static int WIDTH = ResourceMgr.tankD.getWidth();
    public static int HEIGHT = ResourceMgr.tankD.getHeight();

    /**
     * 主方法
     */
    private TankFrame tf = null;

    /**
     * true移动，false停止
     */
    private boolean moving = false;

    /**
     * 坦克构造方法
     * @param x x轴
     * @param y y轴
     * @param dir 方向
     * @param tf 主方法
     */
    public Tank(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    /**
     * 画出坦克
     */
    public void paint(Graphics g) {

        //黑方块
//        Color color = g.getColor();
        /*g.setColor(Color.YELLOW);
        g.fillRect(x, y, 50, 50);
        g.setColor(color);*/
//        g.setColor(color);

        //坦克
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.tankL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD, x, y, null);
                break;
        }


        move();
    }

    /**
     * 坦克移动
     */
    private void move() {

        if (!moving) return;
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void fire() {
        //正方形情况下：x+2分1之坦克宽度 - 子弹2分1宽度
        int bX = this.x + WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = this.y + HEIGHT / 2 - Bullet.HEIGHT / 2;
        tf.bullets.add(new Bullet(bX + 1, bY + 4, this.dir, this.tf));
    }
}
