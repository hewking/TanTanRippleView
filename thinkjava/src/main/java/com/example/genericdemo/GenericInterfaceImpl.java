package com.example.genericdemo;

/**
 * Created by Administrator on 2016/8/30.
 * <p/>
 * 联系方式：。。。
 */
public class GenericInterfaceImpl implements GenericInterface<GenericClass<String>> {
    @Override
    public GenericClass<String> get() {
        return new GenericClass<>("haha");
    }
}
