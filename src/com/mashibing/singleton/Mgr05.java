package com.mashibing.singleton;

/**
 * 懒汉式
 * 线程不安全(因为锁和null没有在一起判断)
 */
public class Mgr05 {

    private static Mgr05 INSTANCE;

    private Mgr05(){}

    public static Mgr05 getInstance(){
        if (null == INSTANCE){
            synchronized (Mgr05.class){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                INSTANCE = new Mgr05();
            }
        }
        return INSTANCE;
    }

    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()-> System.out.println(Mgr05.getInstance().hashCode())).start();
        }
    }

}
