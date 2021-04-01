package com.mashibing.tank;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {

    /*static Properties props = new Properties();

    static {
        try {
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key){
        if (null != props){
            return props.get(key);
        }else {
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(props.getProperty("initTankCount"));
    }*/

    /*********上方为普通写法********/
    /*********下方为单例写法********/
    private static final PropertyMgr INSTANCE = new PropertyMgr();

    private Properties prop;

    private PropertyMgr() {
        prop = new Properties();
        try {
            prop.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PropertyMgr getInstance(){
        return INSTANCE;
    }

    public Object getKey(String key){
        if (null == prop) return null;
            return prop.get(key);
    }

}
