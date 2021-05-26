package com.mashibing.tank.abstractfactory;

import com.mashibing.tank.Audio;
import com.mashibing.tank.ResourceMgr;
import com.mashibing.tank.TankFrame;

import java.awt.*;

public class RectExplode extends BaseExplode {
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
    public RectExplode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;

        new Thread(() -> new Audio("audio/explode.wav").play()).start();
    }

    /**
     * 画出爆炸
     *
     * @param g 画笔
     */
    @Override
    public void paint(Graphics g) {
//        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillRect(x, y, 30 * step, 30 * step);
        step++;
        //大到一定程度是删除
        if (step >= 5) {
            step = 0;
            tf.explodes.remove(this);
        }
        g.setColor(c);
    }
}
