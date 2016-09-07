package com.example.genericdemo;

/**
 * Created by Administrator on 2016/8/30.
 * <p/>
 * 联系方式：。。。
 */
public class GenericClass<T> {

    T t;

    GenericClass(T t){
        this.t = t;
    }

    public T get(){
        return t;
    }

}
