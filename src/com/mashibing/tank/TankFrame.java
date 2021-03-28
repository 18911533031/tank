package com.mashibing.tank;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {

    /**
     * new出主坦克
     */
    Tank myTank = new Tank(200, 400, Dir.UP, Group.GOOD, this);
    /**
     * 子弹列表
     */
    List<Bullet> bullets = new ArrayList<>();
    /**
     * 敌人坦克
     */
    List<Tank> tanks = new ArrayList<>();
    /**
     * 爆炸效果
     */
    Explode e = new Explode(100, 100, this);
    /**
     * 爆炸集合
     */
    List<Explode> explodes = new ArrayList<>();

    /**
     * 游戏的宽和高
     */
    static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;

    /**
     * 生成游戏界面
     */
    public TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("tank 陈珂");
        setVisible(true);
        addKeyListener(new MyKeyListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private Image offScreenImage = null;

    /**
     * 双缓冲机制，防止坦克闪烁
     *
     * @param g 画笔
     */
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);

    }

    /**
     * paint方法，自动调用，调用该方法前会先调用update方法
     *
     * @param g 画笔
     */
    @Override
    public void paint(Graphics g) {

        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹数量：" + bullets.size(), 10, 60);
        g.drawString("敌人数量：" + tanks.size(), 10, 80);
        g.setColor(c);

        //画出主坦克
        myTank.paint(g);
        //画出子弹
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }
        //画出敌人坦克
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }
        //爆炸效果
//        e.paint(g);
        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }

        //碰撞检测
        for (int j = 0; j < tanks.size(); j++) {
            for (int i = 0; i < bullets.size(); i++) {
                bullets.get(i).collidewith(tanks.get(j));
            }
        }
        //x += 10;
        //y += 10;
    }

    /**
     * 内部类，获取键盘操作
     */
    class MyKeyListener extends KeyAdapter {

        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        /**
         * 按键按下
         *
         * @param e 获取按键
         */
        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }

            setMainTanDir();
        }

        /**
         * 按键抬起
         *
         * @param e 获取按键
         */
        @Override
        public void keyReleased(KeyEvent e) {

            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                default:
                    break;
            }
            setMainTanDir();
        }

        /**
         * set方向
         */
        private void setMainTanDir() {

            if (!bL && !bU && !bR && !bD) myTank.setMoving(false);
            else {
                myTank.setMoving(true);
                if (bL) myTank.setDir(Dir.LEFT);
                if (bU) myTank.setDir(Dir.UP);
                if (bR) myTank.setDir(Dir.RIGHT);
                if (bD) myTank.setDir(Dir.DOWN);
            }
        }
    }
}
