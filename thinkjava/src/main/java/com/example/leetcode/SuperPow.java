package com.example.leetcode;

/**
 * Created by Administrator on 2016/9/12.
 * <p>
 * 联系方式：。。。
 */
public class SuperPow {


    public static void main(String[] args){
//        int result = superPow(2,new int[]{1,0,2,4});
        String result = int2Array(1024);
        System.out.println(result);
    }


    public static int superPow(int a, int[] b) {

        int index = 0;
        for(Integer i : b){
            index = index * 10 + i;
        }

        int result = 1;

        for(int i = 0 ; i < index ; i ++){
//            System.out.println(i + " : " + result);
            result *= a;
        }

        return result;
    }

    public static String int2Array(int a){
        StringBuilder sb = new StringBuilder();
        while (a / 10 != 0){
            int mode  = a % 10;
            a = a / 10;
            sb.append(mode);
        }
        sb.append(a);
        return sb.toString();
    }

}
