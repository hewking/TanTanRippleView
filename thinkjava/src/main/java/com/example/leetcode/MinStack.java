package com.example.leetcode;

/**
 * Created by Administrator on 2016/9/12.
 * <p/>
 * 联系方式：。。。
 */
public class MinStack {

    Node head;
    int size;
    int elementCount;

    /** initialize your data structure here. */
    public MinStack() {
        head = new Node();
        size = 10;
    }

    public boolean isFull(){
        if (elementCount == size){
            return true;
        }else {
            return false;
        }
    }

    public void push(int x) {
        if(isFull()){
            size = size * 2;
        }
        Node node = new Node();
        node.node = head;
        node.value = x;
        if(elementCount == 0){
            node.min = x;
        }else{
            node.min = Math.min(x,head.min);
        }
        head = node;
        elementCount ++ ;
    }

    public void pop() {
        head = head.node;
        elementCount --;
    }

    public int top() {
        return head.value;
    }

    public int getMin() {
        return head.min;
    }

    class Node{
        int value;
        int min;
        Node node;
    }
}


/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
