package com.example.leetcode;

/**
 * Created by Administrator on 2016/9/12.
 * <p>
 * 联系方式：。。。
 */
public class SuperPow {


    public static void main(String[] args){
        int result = superPow(2,new int[]{1,0,2,4});
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

}
