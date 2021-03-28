package com.mashibing.tank;

import java.awt.*;

public class Explode {

    private int x = 300, y = 300;

    /**
     * 子弹的宽和高
     */
    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();
    /**
     * 主方法
     */
    private TankFrame tf = null;
    /**
     * 是否移除
     */
    private boolean living = true;

    private int step = 0;

    /**
     * 爆炸构造方法
     *
     * @param x  x轴
     * @param y  y轴
     * @param tf 主方法
     */
    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;

        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    /**
     * 画出爆炸
     *
     * @param g 画笔
     */
    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++], x, y, null);

        if (step >= ResourceMgr.explodes.length) {
            step = 0;
            tf.explodes.remove(this);
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

}
