package com.example;

public class QuickSort {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] arr = {49,38,65,97,76,13,27};
        quickSort(0,arr.length - 1,arr);
        System.out.println("ssss");
        for (int i : arr) {
            System.out.println(i);
        }
    }

    public static void quickSort(int left , int right , int[] arr) {
        if(left > right){
            return;
        }

        int part = partition(left,right,arr);
        quickSort(left,part - 1,arr);
        quickSort(part + 1, right,arr);
    }

    public static int partition(int left , int right ,int[] arr){
        int k = left;
        while(left < right){
            while(left < right && arr[right] >= arr[k])
                right --;
                arr[left] = arr[right];
            while(left < right && arr[left] <= arr[k])
                left ++;
                arr[right] = arr[left];
        }
        arr[left] = arr[k];
        return left;
    }

}
