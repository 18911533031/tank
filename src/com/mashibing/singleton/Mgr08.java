package com.mashibing.singleton;

public enum Mgr08 {

    INSTANCE;

    private void m(){}

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()-> System.out.println(Mgr08.INSTANCE.hashCode())).start();
        }
    }
}
