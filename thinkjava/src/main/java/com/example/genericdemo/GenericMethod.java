package com.example.genericdemo;

/**
 * Created by Administrator on 2016/8/30.
 * <p/>
 * 联系方式：。。。
 */
public class GenericMethod<T,U> {

    public T get(T t,U u){
        return t;
    }

    public <T> T getMethod(T t){
        return t;
    }

    public T  getObject(Class<T> c){
        try {
            return c.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
