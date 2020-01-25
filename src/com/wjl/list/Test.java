package com.wjl.list;

import com.wjl.list.linkelist.MyLinkedList;

public class Test {


    public static void main(String[] args) {
        MyLinkedList<String> myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("1");
        myLinkedList.add("2");
        myLinkedList.add("3");
        myLinkedList.add("4");
        System.out.println(myLinkedList.toString());
        myLinkedList.add(0,"5");
        System.out.println(myLinkedList.toString());
        myLinkedList.remove(0);
        System.out.println(myLinkedList.toString());
    }
}
