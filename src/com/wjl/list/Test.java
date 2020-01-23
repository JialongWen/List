package com.wjl.list;

import com.wjl.list.arraylist.MyArrayList;
import com.wjl.list.arraylist.MyList;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Test {


    public static void main(String[] args) {
        MyList<String> myList = new MyArrayList<String>();
        myList.add("a");
        myList.add("b");
        myList.add("e");
        myList.add("f");
        myList.add("g");
        System.out.println(myList.toString());
        myList.add(3,"h");
        System.out.println(myList.toString());
    }
}
