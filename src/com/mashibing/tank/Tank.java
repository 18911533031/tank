package com.mashibing.tank;

import com.mashibing.tank.abstractfactory.BaseTank;

import java.awt.*;
import java.util.Random;

public class Tank extends BaseTank {

    private int x, y;

    /**
     * 方向
     */
    private Dir dir = Dir.UP;

    /**
     * 移动速度
     */
    private static final int SPEED = 5;

    /**
     * 是否活着
     */
    private boolean living = true;

    /**
     * 发射子弹随机
     */
    private Random random = new Random();

    /**
     * 区分队伍--放在了父类
     */
//    private Group group = Group.GOOD;

    public boolean isLiving() {
        return living;
    }

    public void setLiving(boolean living) {
        this.living = living;
    }

    /**
     * 图片的宽高
     */
    public static int WIDTH = ResourceMgr.badTankU.getWidth();
    public static int HEIGHT = ResourceMgr.badTankU.getHeight();

    /**
     * 主方法
     */
    TankFrame tf = null;

    /**
     * true移动，false停止
     */
    private boolean moving = true;

    private FireStartegy fs;

    /**
     * 坦克构造方法
     *
     * @param x   x轴
     * @param y   y轴
     * @param dir 方向
     * @param tf  主方法
     */
    public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;

        rect.x = x;
        rect.y = y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        /**
         * 主角光环，发射四个方向子弹
         */
        PropertyMgr propertyMgr = PropertyMgr.getInstance();
        if (group == Group.BAD){
            String defaultFire = (String) propertyMgr.getKey("defaultFire");
            try {
                fs = (FireStartegy) Class.forName(defaultFire).newInstance();
            } catch (Exception e){
                e.printStackTrace();
            }
//            fs = new DefaultFireStartegy();
        }else {
            String fourDirFire = (String) propertyMgr.getKey("fourDirFire");
            try {
                fs = (FireStartegy) Class.forName(fourDirFire).newInstance();
            } catch (Exception e){
                e.printStackTrace();
            }
//            fs = new FourDirFireStartegy();
        }
    }

    /**
     * 画出坦克
     */
    public void paint(Graphics g) {

        if (!living) {
            tf.tanks.remove(this);
        }

        //黑方块
//        Color color = g.getColor();
        /*g.setColor(Color.YELLOW);
        g.fillRect(x, y, 50, 50);
        g.setColor(color);*/
//        g.setColor(color);

        //坦克
        switch (dir) {
            case LEFT:
                g.drawImage(this.group == Group.BAD ? ResourceMgr.badTankL : ResourceMgr.goodTankL, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.BAD ? ResourceMgr.badTankU : ResourceMgr.goodTankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.BAD ? ResourceMgr.badTankR : ResourceMgr.goodTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.BAD ? ResourceMgr.badTankD : ResourceMgr.goodTankD, x, y, null);
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

        //随机发射子弹
        if (random.nextInt(100) > 95 && this.group == Group.BAD) this.fire();

        //随机变换方向
        if (this.group == Group.BAD && random.nextInt(100) > 96)
            randomDir();

        //边界检测
        boundsCheck();

        rect.x = this.x;
        rect.y = this.y;
    }

    /**
     * 边界检测
     */
    private void boundsCheck() {
        if (this.x < 2) x = 2;
        if (this.y < 28) y = 28;
        if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH - 2) x = TankFrame.GAME_WIDTH - Tank.WIDTH - 2;
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2) y = TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2;
    }

    /**
     * 随机变换方向
     */
    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
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
//        fs.fire(this);
        int bX = this.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = this.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;

        Dir[] values = Dir.values();
        for (Dir dir : values) {
            this.tf.gf.createBullet(bX + 1, bY + 4, dir, this.getGroup(), this.tf);
        }

        if(this.getGroup() == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }

    public void die() {
        this.living = false;
    }
}
