package com.example.leetcode;

/**
 * Created by Administrator on 2016/9/13.
 * <p>
 * 联系方式：。。。
 */
public class ReverseLinkedList {


    public static void main(String[] args){
        ReverseLinkedList linkedList = new ReverseLinkedList();
        linkedList.push(1);
        linkedList.push(2);
        linkedList.push(3);
        linkedList.push(4);
        linkedList.push(5);
        linkedList.push(6);
        ListNode listNode = linkedList.reverseList(linkedList.head);
        while (listNode.next!= null){
            System.out.println(listNode.next.val);
            listNode = listNode.next;
        }
    }

    ListNode head;

    ReverseLinkedList(){
        head = null;
    }

    public void push(int x){
        if(head == null){
            head = new ListNode(x);
            ListNode nextNode = new ListNode(x);
            head.next = nextNode;
            return;
        }
        ListNode listNode = new ListNode(x);
        listNode.next = head.next;
        head.next = listNode;
    }

    /**
     *
     * @param head
     * @return
     */

    public ListNode reverseList(ListNode head) {
        ListNode tail = null;
        while(head.next != null){
            ListNode listNode = new ListNode(head.next.val);
            System.out.println(head.next.val);
            listNode.next = tail;
            tail = listNode;
            head = head.next;
        }
        System.out.println("//////////");
        ListNode node = new ListNode(0);
        node.next = tail;
        return node;
    }

    public class ListNode{
        int val;
        ListNode next;
        ListNode(int x){val = x;}
    }

}
