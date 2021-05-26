package com.mashibing.tank;

public class Main {

    /**
     * 执行方法
     * @param args 无
     * @throws InterruptedException 异常
     */
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();
        int initTankCount = Integer.parseInt((String)PropertyMgr.getInstance().getKey("initTankCount"));

        for (int i = 0; i < initTankCount; i++) {
            tf.tanks.add(tf.gf.createTank(80 + i* 60, 80, Dir.DOWN, Group.BAD, tf));
        }

        new Thread(()->new Audio("audio/war1.wav").play()).start();

        while (true){
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
