package com.mashibing.tank;

public class Main {

    /**
     * 执行方法
     * @param args 无
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        while (true){
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
