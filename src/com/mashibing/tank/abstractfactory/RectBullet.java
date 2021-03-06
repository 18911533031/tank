package com.mashibing.tank.abstractfactory;

import com.mashibing.tank.*;

import java.awt.*;

public class RectBullet extends BaseBullet {

    /**
     * 移动速度
     */
    private static final int SPEED = 10;
    private int x = 300, y = 300;

    Rectangle rect = new Rectangle();

    /**
     * 方向
     */
    private Dir dir;

    /**
     * 子弹的宽和高
     */
    public static int WIDTH = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();
    /**
     * 主方法
     */
    private TankFrame tf = null;
    /**
     * 是否移除
     */
    private boolean living = true;

    /**
     * 子弹区分队伍
     */
    private Group group = Group.BAD;

    /**
     * 子弹构造方法
     *
     * @param x   x轴
     * @param y   y轴
     * @param dir 方向
     * @param tf  主方法
     */
    public RectBullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;

        rect.x = x;
        rect.y = y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        tf.bullets.add(this);
    }

    /**
     * 画出子弹
     *
     * @param g 画笔
     */
    public void paint(Graphics g) {
        //判断子弹是否移除
        if (!living) {
            tf.bullets.remove(this);
        }

        //炮弹圆圈块
        /*Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x, y, 30, 30);
        g.setColor(c);*/
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, 20, 20);
        g.setColor(c);


        move();

        rect.x = x;
        rect.y = y;
    }

    /**
     * 子弹移动
     */
    private void move() {

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
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) living = false;

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

    /**
     * 碰撞检测
     *
     * @param tank 当前坦克
     */
    @Override
    public void collidewith(BaseTank tank) {
        if (this.group == tank.getGroup()) return;

        //TODO 用一个rect来处理，现在方法每次循环都要new出多个对象 --已解决
//        Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
//        Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
        if (rect.intersects(tank.rect)) {
            this.die();
            tank.die();
            int eX = tank.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
            int eY = tank.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
            tf.explodes.add(tf.gf.createExplode(eX, eY, tf));
        }
    }

    private void die() {
        this.living = false;
    }
}
