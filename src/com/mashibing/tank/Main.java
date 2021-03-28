package com.mashibing.tank;

public class Main {

    /**
     * 执行方法
     * @param args 无
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        for (int i = 0; i < 5; i++) {
            tf.tanks.add(new Tank(80 + i* 60, 80, Dir.DOWN, Group.BAD, tf));
        }

        new Thread(()->new Audio("audio/war1.wav").play()).start();

        while (true){
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
