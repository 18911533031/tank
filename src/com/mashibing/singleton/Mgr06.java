package com.mashibing.singleton;

/**
 * 比较完美的写法
 * 按需初始化
 *
 */
public class Mgr06 {

    //双重判断需要加上volatile
    private static volatile Mgr06 INSTANCE;

    private Mgr06() {
    }

    public static Mgr06 getInstance() {
        if (null == INSTANCE){
            synchronized (Mgr06.class){
                //双重检查
                if (null == INSTANCE){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new Mgr06();
                }
            }
        }
        return INSTANCE;
    }

    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()-> System.out.println(Mgr06.getInstance().hashCode())).start();
        }
    }
}
