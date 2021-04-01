package com.mashibing.singleton;

/**
 * 懒汉式 》》 可行《线程安全》
 * 虽然达到了按需初始化的目的，但是带来线程不安全
 * 可以加锁保证线程安全，但是效率下降（每次调用需要申请锁导致效率下降）
 */
public class Mgr04 {

    private static Mgr04 INSTANCE;

    private Mgr04() {
    }

    public static synchronized Mgr04 getInstance() {
        if (null == INSTANCE) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new Mgr04();
        }
        return INSTANCE;
    }

    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()-> System.out.println(Mgr04.getInstance().hashCode())).start();
        }
    }

}
