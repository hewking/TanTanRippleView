package com.example.genericdemo;

/**
 * Created by Administrator on 2016/8/30.
 * <p/>
 * 联系方式：。。。
 */
public class GenericTest{

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
//        泛型类
//        GenericClass genericClass = new GenericClass<>(6);
//        System.out.println(genericClass.get());

//        GenericInterfaceImpl genericInterface = new GenericInterfaceImpl();
//        System.out.println(genericInterface.get().get());
//

        GenericMethod<Integer, Integer> genericMethod = new GenericMethod<>();
        Integer object = genericMethod.getObject(Integer.class);

    }



}
